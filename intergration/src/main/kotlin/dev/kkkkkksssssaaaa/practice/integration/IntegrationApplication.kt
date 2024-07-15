package dev.kkkkkksssssaaaa.practice.integration

import org.json.JSONObject

class IntegrationApplication

fun main(args: Array<String>) {
    val notionData = getNotionData()
    val content = JSONObject(notionData).toString(4) // JSON 데이터를 포맷팅하여 문자열로 변환
    updateGitHubRepo(content)
}

