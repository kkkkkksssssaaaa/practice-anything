package common.factory

import mu.KotlinLogging
import java.net.URLDecoder
import java.io.File

object BeanRegistry {
    private val log = KotlinLogging.logger {}

    fun init(basePackage: String) {
        scanClasses(basePackage).forEach {
            if (!it.isAnnotationPresent(Component::class.java)) return@forEach

            val instance = it.getDeclaredConstructor().newInstance()

            Beans.push(it.simpleName, instance)

            log.info("Registered bean: ${it.simpleName}")
        }
    }

    private fun scanClasses(packageName: String): List<Class<*>> {
        val path = packageName.replace('.', '/')

        val classLoader = Thread.currentThread().contextClassLoader

        val resource = classLoader.getResource(path) ?: return emptyList()
        val file = File(URLDecoder.decode(resource.file, "UTF-8"))

        return file.walkTopDown()
            .filter { it.isFile && it.extension == "class" }
            .map {
                val className = packageName + "." + it.nameWithoutExtension
                Class.forName(className)
            }
            .toList()
    }
}

