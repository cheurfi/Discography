package com.cheurfi.network

import com.cheurfi.network.musicbrainz.data.Artist
import com.cheurfi.network.musicbrainz.data.Artist.*
import com.cheurfi.network.musicbrainz.data.Artists

object ArtistsFixture {

    fun artists(
        artists: List<Artist> = listOf(artist()),
        count: Int = 0,
        created: String = "",
        offset: Int = 0,
    ): Artists {
        return Artists(
            artists = artists,
            count = count,
            created = created,
            offset = offset,
        )
    }

    fun artist(
        aliases: List<Alias>? = null,
        area: Area? = null,
        beginArea: BeginArea? = null,
        country: String? = null,
        disambiguation: String? = null,
        id: String = "id",
        lifeSpan: LifeSpan? = null,
        name: String = "name",
        score: String? = null,
        sortName: String? = null,
        tags: List<Tag>? = emptyList(),
        type: String? = null,
    ): Artist {
        return Artist(
            aliases = aliases,
            area = area,
            beginArea = beginArea,
            country = country,
            disambiguation = disambiguation,
            id = id,
            lifeSpan = lifeSpan,
            name = name,
            score = score,
            sortName = sortName,
            tags = tags,
            type = type,
        )
    }

}