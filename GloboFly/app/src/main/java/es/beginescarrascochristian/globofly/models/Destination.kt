package es.beginescarrascochristian.globofly.models

data class Destination(
    var id: Int=0,
    var city: String? = null,
    var description: String? = null,
    var country: String? = null
)