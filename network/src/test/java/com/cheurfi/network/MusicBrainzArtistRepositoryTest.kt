package com.cheurfi.network

import com.cheurfi.network.ArtistsFixture.artist
import com.cheurfi.network.ArtistsFixture.artists
import com.cheurfi.network.musicbrainz.ArtistService
import com.cheurfi.network.musicbrainz.MusicBrainzArtistRepository
import com.cheurfi.network.musicbrainz.RecordResponse
import com.cheurfi.network.musicbrainz.RecordService
import com.cheurfi.repository.data.Artist
import com.cheurfi.repository.data.Recording
import com.cheurfi.utils.coroutines.DispatcherProvider
import com.flextrade.jfixture.JFixture
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations.openMocks

@OptIn(ExperimentalCoroutinesApi::class)
internal class MusicBrainzArtistRepositoryTest {

    @Mock
    lateinit var mockArtistService: ArtistService

    @Mock
    lateinit var mockRecordService: RecordService

    @Mock
    lateinit var mockDispatcher: DispatcherProvider

    @Before
    fun setUp() {
        openMocks(this)
    }

    @Test
    fun `getArtists returns empty list when service throws`() = runTest {
        val testDispatcher = UnconfinedTestDispatcher()
        `when`(mockDispatcher.computation()).thenReturn(testDispatcher)

        val repository = MusicBrainzArtistRepository(
            artistService = mockArtistService,
            recordService = mockRecordService,
            dispatcher = mockDispatcher,
        )

        `when`(mockArtistService.getArtist(artist = "Miguel")).thenThrow(RuntimeException())

        val actual = repository.getArtists(artist = "Miguel")
        assert(actual.equals(emptyList<Artist>()))
    }

    @Test
    fun `getArtists returns artists`() = runTest {
        val testDispatcher = UnconfinedTestDispatcher()
        `when`(mockDispatcher.computation()).thenReturn(testDispatcher)

        val artists = artists(
            artists = listOf(
                artist(
                    id = "1",
                    name = "Jose Gonzales"
                ),
                artist(
                    id = "2",
                    name = "Pharrell Williams"
                ),
            )
        )
        `when`(mockArtistService.getArtist(artist = "Miguel")).thenReturn(artists)

        val repository = MusicBrainzArtistRepository(
            artistService = mockArtistService,
            recordService = mockRecordService,
            dispatcher = mockDispatcher,
        )

        val actual = repository.getArtists(artist = "Miguel")
        assert(
            actual == listOf(
                Artist(
                    id = "1",
                    name = "Jose Gonzales"
                ),
                Artist(
                    id = "2",
                    name = "Pharrell Williams"
                ),
            )
        )
    }

    @Test
    fun `getRecording returns empty list when service throws`() = runTest {
        val testDispatcher = UnconfinedTestDispatcher()
        `when`(mockDispatcher.computation()).thenReturn(testDispatcher)

        val repository = MusicBrainzArtistRepository(
            artistService = mockArtistService,
            recordService = mockRecordService,
            dispatcher = mockDispatcher,
        )

        `when`(mockRecordService.getRecordings(artistId = "1")).thenThrow(RuntimeException())

        val actual = repository.getRecordings(artistId = "1")
        assert(actual.equals(emptyList<Artist>()))
    }

    @Test
    fun `getRecording returns artists`() = runTest {
        val testDispatcher = UnconfinedTestDispatcher()
        `when`(mockDispatcher.computation()).thenReturn(testDispatcher)

        val recordResponse = JFixture().create(RecordResponse::class.java)
        `when`(mockRecordService.getRecordings(artistId = "1")).thenReturn(recordResponse)

        val repository = MusicBrainzArtistRepository(
            artistService = mockArtistService,
            recordService = mockRecordService,
            dispatcher = mockDispatcher,
        )

        val actual = repository.getRecordings(artistId = "1")
        assert(
            actual == recordResponse.recordings.map {
                Recording(
                    title = it.title,
                    firstReleaseDate = it.firstReleaseDate,
                )
            }
        )
    }
}
