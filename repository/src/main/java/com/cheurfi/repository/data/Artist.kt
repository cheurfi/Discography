package com.cheurfi.repository.data

import com.google.gson.annotations.SerializedName

data class Artist(
    val aliases: List<Alias>?,
    val area: Area?,
    @SerializedName("begin-area")
    val beginArea: BeginArea?,
    val country: String?,
    val disambiguation: String?,
    val id: String,
    @SerializedName("life-span")
    val lifeSpan: LifeSpan?,
    val name: String,
    val score: String?,
    @SerializedName("sort-name")
    val sortName: String?,
    val tags: List<Tag>?,
    val type: String?,
) {
    data class Alias(
        @SerializedName("begin-date")
        val beginDate: String?,
        @SerializedName("end-date")
        val endDate: String?,
        @SerializedName("locale")
        val locale: String,
        val name: String,
        val primary: String?,
        @SerializedName("sort-name")
        val sortName: String,
        val type: String?,
    )

    data class BeginArea(
        val id: String,
        val name: String,
        @SerializedName("sort-name")
        val sortName: String,
    )

    data class LifeSpan(
        val begin: String,
        val end: String,
        val ended: Boolean,
    )

    data class Area(
        val id: String,
        val name: String,
        @SerializedName("sort-name")
        val sortName: String,
    )

    data class Tag(
        val count: Int,
        val name: String,
    )
}