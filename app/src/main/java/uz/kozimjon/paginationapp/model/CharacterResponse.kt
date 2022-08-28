package uz.kozimjon.paginationapp.model

data class CharacterResponse(val info: Info, val results: List<ResultData>) {

    data class Info (
        val count: Int?,
        val pages: String?,
        val next: String?,
        val prev: String?
        )

    data class ResultData (
        val id: Int?,
        val name: String?,
        val status: String?,
        val species: String?,
        val type: String?,
        val gender: String?,
        val image: String?,
        val created: String?
    )
}