package dev.kkkkkksssssaaaa.practice.realtimealarmsystem.common.configuration.initialize

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import dev.kkkkkksssssaaaa.practice.realtimealarmsystem.domain.artist.dto.ArtistDto
import dev.kkkkkksssssaaaa.practice.realtimealarmsystem.domain.artist.dto.GroupDto
import dev.kkkkkksssssaaaa.practice.realtimealarmsystem.domain.artist.persistance.ArtistRepository
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.nio.file.Files
import java.nio.file.Paths

@Configuration
class InitializeArtist(
    private val artistRepository: ArtistRepository
) {
    private val log = LoggerFactory.getLogger(javaClass)

    @Bean
    fun doInitializeArtist(): Boolean {
        log.info("Initialize Artist")

        val mapper = jacksonObjectMapper()

        val path = Paths.get(ClassLoader.getSystemResource("artist.json").toURI())
        val jsonString = Files.readString(path)

        val artists: List<InitializeArtistDto> = mapper.readValue(jsonString)

        artists.forEach { artist ->
            artistRepository.save(
                ArtistDto(
                    name = artist.name,
                    group = artist.group?.let { group ->
                        GroupDto(
                            name = group
                        )
                    }
                )
            )
        }

        return true
    }
}