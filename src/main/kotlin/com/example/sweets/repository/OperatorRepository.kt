package com.example.sweets.repository

import com.example.sweets.model.storage.Operator
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface OperatorRepository : CrudRepository<Operator, Long>