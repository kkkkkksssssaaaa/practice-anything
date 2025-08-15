package common.factory

import common.extension.getBeanName
import common.extension.isBean
import mu.KotlinLogging
import java.net.URLDecoder
import java.io.File

object BeanRegistry {
    private val log = KotlinLogging.logger {}

    fun init(basePackage: String) {
        val targets = scanClasses(basePackage)

        targets.forEach {
            if (it.isInterface || it.isEnum) return@forEach
            if (!it.isBean(Component::class)) return@forEach

            val instance = it.getDeclaredConstructor().newInstance()
            val name = it.getBeanName(Component::class)

            Beans.push(name, instance)

            log.info("Registered bean: $name")
        }
    }

    private fun scanClasses(basePackageName: String): List<Class<*>> {
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

                Class.forName(className)
            }
            .toList()
    }
}

