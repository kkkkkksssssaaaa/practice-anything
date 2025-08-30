package common.factory

import common.extension.getBeanName
import common.extension.isBean
import common.extension.isEnum
import common.extension.isInterface
import mu.KotlinLogging
import java.io.File
import java.net.URLDecoder
import kotlin.reflect.KClass
import kotlin.reflect.full.primaryConstructor

object BeanRegistry {
    private val log = KotlinLogging.logger {}

    fun init(basePackage: String) {
        val targets = scanClasses(basePackage)

        val classMap = targets
            .filter { !it.isEnum() }
            .filter { it.isBean() }
            .filter { !it.isInterface() }
            .associateBy { it.getBeanName() }

        classMap.keys.forEach { name ->
            getOrCreate(name, classMap)
        }
    }

    private fun scanClasses(basePackageName: String): List<KClass<*>> {
        val path = basePackageName.replace('.', '/')

        val classLoader = Thread.currentThread().contextClassLoader

        val resource = classLoader.getResource(path) ?: return emptyList()
        val file = File(URLDecoder.decode(resource.file, "UTF-8"))

        return file.walkTopDown()
            .filter { it.isFile && it.extension == "class" }
            .map {
                val extractPath = it.path.split("$basePackageName/")[1]
                    .split("/")

                val packageName = extractPath.subList(0, extractPath.size - 1)
                    .joinToString(separator = ".")

                val className = "${basePackageName}.${packageName}.${it.nameWithoutExtension}"

                log.info("Scanning class: $className")

                Class.forName(className).kotlin
            }
            .toList()
    }

    private fun getOrCreate(name: String, classMap: Map<String, KClass<*>>): Any {
        Beans.find<Any>(name)?.let {
            return it
        }

        val clazz = classMap[name] ?: throw IllegalArgumentException("No class found for bean: $name")

        if (clazz.constructors.size > 1) {
            throw IllegalArgumentException("Multiple constructors found for bean: $clazz")
        }

        val constructor = clazz.primaryConstructor!!

        val params = constructor.parameters.map {
            val dependencyName = it.type.toString().split(".").last()
            getOrCreate(dependencyName, classMap)
        }.map {
            if (it is Bean<*>) {
                return@map it.originInstance()
            }

            return it
        }.toTypedArray()

        val instance = constructor.call(*params)
        Beans.push(name, Bean(instance, clazz))

        log.info("Registered bean: $name")
        return instance
    }
}

