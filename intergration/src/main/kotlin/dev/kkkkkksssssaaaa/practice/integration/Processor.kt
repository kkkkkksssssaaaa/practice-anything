package dev.kkkkkksssssaaaa.practice.integration

import org.kohsuke.github.GHRepository
import org.kohsuke.github.GitHub
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.nio.file.Paths

private val notionApiKey = System.getenv("NOTION_API_KEY")
private val databaseId = System.getenv("NOTION_DATABASE_ID")
private val githubToken = System.getenv("GITHUB_TOKEN")
private val repoName = System.getenv("GITHUB_REPO_NAME")
private val filePath = System.getenv("FILE_PATH")

fun getNotionData(): String {
    val url = URL("https://api.notion.com/v1/databases/$databaseId/query")
    val connection = url.openConnection() as HttpURLConnection
    connection.requestMethod = "POST"
    connection.setRequestProperty("Authorization", "Bearer $notionApiKey")
    connection.setRequestProperty("Notion-Version", "2021-08-16")
    connection.doOutput = true

    val responseCode = connection.responseCode
    val response = StringBuffer()

    if (responseCode == HttpURLConnection.HTTP_OK) {
        BufferedReader(InputStreamReader(connection.inputStream)).use { `in` ->
            var inputLine: String?
            while (`in`.readLine().also { inputLine = it } != null) {
                response.append(inputLine)
            }
        }
    } else {
        throw RuntimeException("Failed : HTTP error code : $responseCode")
    }

    return response.toString()
}

fun updateGitHubRepo(content: String) {
    val github = GitHub.connectUsingOAuth(githubToken)
    val repo: GHRepository = github.getRepository(repoName)
    val path = Paths.get(filePath)

    try {
        val file = repo.getFileContent(filePath)
        file.update(content, "Update from Notion")
    } catch (e: Exception) {
        repo.createContent(content, "Create from Notion", filePath)
    }
}