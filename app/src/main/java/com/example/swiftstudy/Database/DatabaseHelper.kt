package com.example.swiftstudy.Database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(CREATE_TABLE_USERS)
        db.execSQL(CREATE_TABLE_SUBJECTS)
        db.execSQL(CREATE_TABLE_NOTES)
        db.execSQL(CREATE_TABLE_FLASHCARDS)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_USERS")
        db.execSQL("DROP TABLE IF EXISTS $TABLE_SUBJECTS")
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NOTES")
        db.execSQL("DROP TABLE IF EXISTS $TABLE_FLASHCARDS")
        onCreate(db)
    }

    // User operations
    fun addUser(email: String, password: String): Boolean {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_EMAIL, email)
            put(COLUMN_PASSWORD, password)
        }
        val result = db.insert(TABLE_USERS, null, values)
        db.close()
        return result != -1L
    }


    fun checkUser(email: String, password: String): Boolean {
        val db = readableDatabase
        val cursor = db.query(
            TABLE_USERS,
            arrayOf(COLUMN_ID),
            "$COLUMN_EMAIL = ? AND $COLUMN_PASSWORD = ?",
            arrayOf(email, password),
            null, null, null
        )
        val isUserExists = cursor.count > 0
        if (!isUserExists) {
            println("No user found with email: $email and password: $password")
        }
        cursor.close()
        db.close()
        return isUserExists
    }


    // Subject operations
    fun addSubject(userId: Int, name: String): Boolean {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_USER_ID, userId)
            put(COLUMN_SUBJECT_NAME, name)
        }
        val result = db.insert(TABLE_SUBJECTS, null, values)
        db.close()
        return result != -1L
    }

    fun getSubjects(userId: Int): List<String> {
        val db = readableDatabase
        val cursor = db.query(
            TABLE_SUBJECTS,
            arrayOf(COLUMN_SUBJECT_NAME),
            "$COLUMN_USER_ID = ?",
            arrayOf(userId.toString()),
            null, null, null
        )
        val subjects = mutableListOf<String>()
        while (cursor.moveToNext()) {
            subjects.add(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_SUBJECT_NAME)))
        }
        cursor.close()
        db.close()
        return subjects
    }

    // Notes operations
    fun addNote(subjectId: Int, title: String, content: String): Boolean {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_SUBJECT_ID, subjectId)
            put(COLUMN_NOTE_TITLE, title)
            put(COLUMN_NOTE_CONTENT, content)
        }
        val result = db.insert(TABLE_NOTES, null, values)
        db.close()
        return result != -1L
    }

    fun getNotes(subjectId: Int): List<Pair<String, String>> {
        val db = readableDatabase
        val cursor = db.query(
            TABLE_NOTES,
            arrayOf(COLUMN_NOTE_TITLE, COLUMN_NOTE_CONTENT),
            "$COLUMN_SUBJECT_ID = ?",
            arrayOf(subjectId.toString()),
            null, null, null
        )
        val notes = mutableListOf<Pair<String, String>>()
        while (cursor.moveToNext()) {
            val title = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NOTE_TITLE))
            val content = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NOTE_CONTENT))
            notes.add(Pair(title, content))
        }
        cursor.close()
        db.close()
        return notes
    }

    // Flashcards operations
    fun addFlashcard(subjectId: Int, question: String, answer: String): Boolean {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_SUBJECT_ID, subjectId)
            put(COLUMN_QUESTION, question)
            put(COLUMN_ANSWER, answer)
        }
        val result = db.insert(TABLE_FLASHCARDS, null, values)
        db.close()
        return result != -1L
    }

    fun getFlashcards(subjectId: Int): List<Pair<String, String>> {
        val db = readableDatabase
        val cursor = db.query(
            TABLE_FLASHCARDS,
            arrayOf(COLUMN_QUESTION, COLUMN_ANSWER),
            "$COLUMN_SUBJECT_ID = ?",
            arrayOf(subjectId.toString()),
            null, null, null
        )
        val flashcards = mutableListOf<Pair<String, String>>()
        while (cursor.moveToNext()) {
            val question = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_QUESTION))
            val answer = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_ANSWER))
            flashcards.add(Pair(question, answer))
        }
        cursor.close()
        db.close()
        return flashcards
    }
    // Delete a subject
    fun deleteSubject(subjectId: Int): Boolean {
        val db = writableDatabase
        val result = db.delete(TABLE_SUBJECTS, "$COLUMN_ID = ?", arrayOf(subjectId.toString()))
        db.close()
        return result > 0
    }

    // Edit a subject
    fun updateSubject(subjectId: Int, newName: String): Boolean {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_SUBJECT_NAME, newName)
        }
        val result = db.update(TABLE_SUBJECTS, values, "$COLUMN_ID = ?", arrayOf(subjectId.toString()))
        db.close()
        return result > 0
    }


    companion object {
        const val DATABASE_NAME = "swift_study.db"
        const val DATABASE_VERSION = 1

        const val TABLE_USERS = "users"
        const val COLUMN_ID = "id"
        const val COLUMN_EMAIL = "email"
        const val COLUMN_PASSWORD = "password"

        const val TABLE_SUBJECTS = "subjects"
        const val COLUMN_USER_ID = "user_id"
        const val COLUMN_SUBJECT_NAME = "name"

        const val TABLE_NOTES = "notes"
        const val COLUMN_SUBJECT_ID = "subject_id"
        const val COLUMN_NOTE_TITLE = "title"
        const val COLUMN_NOTE_CONTENT = "content"

        const val TABLE_FLASHCARDS = "flashcards"
        const val COLUMN_QUESTION = "question"
        const val COLUMN_ANSWER = "answer"

        private const val CREATE_TABLE_USERS =
            "CREATE TABLE $TABLE_USERS (" +
                    "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "$COLUMN_EMAIL TEXT NOT NULL, " +
                    "$COLUMN_PASSWORD TEXT NOT NULL)"

        private const val CREATE_TABLE_SUBJECTS =
            "CREATE TABLE $TABLE_SUBJECTS (" +
                    "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "$COLUMN_USER_ID INTEGER NOT NULL, " +
                    "$COLUMN_SUBJECT_NAME TEXT NOT NULL, " +
                    "FOREIGN KEY($COLUMN_USER_ID) REFERENCES $TABLE_USERS($COLUMN_ID))"

        private const val CREATE_TABLE_NOTES =
            "CREATE TABLE $TABLE_NOTES (" +
                    "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "$COLUMN_SUBJECT_ID INTEGER NOT NULL, " +
                    "$COLUMN_NOTE_TITLE TEXT NOT NULL, " +
                    "$COLUMN_NOTE_CONTENT TEXT NOT NULL, " +
                    "FOREIGN KEY($COLUMN_SUBJECT_ID) REFERENCES $TABLE_SUBJECTS($COLUMN_ID))"

        private const val CREATE_TABLE_FLASHCARDS =
            "CREATE TABLE $TABLE_FLASHCARDS (" +
                    "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "$COLUMN_SUBJECT_ID INTEGER NOT NULL, " +
                    "$COLUMN_QUESTION TEXT NOT NULL, " +
                    "$COLUMN_ANSWER TEXT NOT NULL, " +
                    "FOREIGN KEY($COLUMN_SUBJECT_ID) REFERENCES $TABLE_SUBJECTS($COLUMN_ID))"
    }
}
