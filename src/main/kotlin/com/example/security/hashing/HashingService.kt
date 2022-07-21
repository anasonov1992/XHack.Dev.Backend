package com.example.security.hashing

interface HashingService {
    fun generateHash(value: String, saltLength: Int = 32): SaltedHash
    fun verify(value: String, saltedHash: SaltedHash): Boolean
}