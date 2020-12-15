package com.example.sweets.repository

import com.example.sweets.model.storage.Chef
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ChefrRepository : CrudRepository<Chef, Long>