package dev.kkkkkksssssaaaa.practice.realtimealarmsystem.domain.artist.persistance

/**
 * 아티스트 상태
 *
 * @property ACTIVATED 사고 안 치고 잘 활동 중
 * @property SUSPENSION 활동 중단
 * @property WITHDRAWAL (그룹인 경우) 탈퇴
 * @property TRANSFER 소속사 옮김
 * @property SELF_RESTRAINT 사고 치고 자숙 중
 * */
enum class ArtistStatus {
    ACTIVATED,
    SUSPENSION,
    WITHDRAWAL,
    TRANSFER,
    SELF_RESTRAINT
}