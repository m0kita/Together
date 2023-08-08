package ru.mokita.together

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import ru.mokita.together.data.source.database.NoteDatabase
import ru.mokita.together.data.source.database.dao.NoteDao
import ru.mokita.together.data.source.database.model.DatabaseNote

@RunWith(AndroidJUnit4::class)
class NoteDatabaseTest {

    private lateinit var noteDao: NoteDao
    private lateinit var database: NoteDatabase

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(context, NoteDatabase::class.java).build()
        noteDao = database.noteDao()
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun insertAndGetLastNote() = runBlocking {
        val note1 = DatabaseNote(1, "Title 1", "Description 1", date = "28 июля")
        val note2 = DatabaseNote(2, "Title 2", "Description 2", date = "27 июля")

        noteDao.insert(note1)
        noteDao.insert(note2)

        val lastNote = noteDao.getNote()
        assertEquals(note2, lastNote)
    }

    @Test
    fun getAllNotes() = runBlocking {
        val note1 = DatabaseNote(1, "Title 1", "Description 1", date = "28 июля")
        val note2 = DatabaseNote(2, "Title 2", "Description 2", date = "27 июля")

        noteDao.insert(note1)
        noteDao.insert(note2)

        val allNotes = noteDao.getAllNotes()
        assertEquals(listOf(note1, note2), allNotes)
    }
}