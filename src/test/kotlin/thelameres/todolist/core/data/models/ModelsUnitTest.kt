package thelameres.todolist.core.data.models

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*


class ModelsUnitTest {
    @Test
    @Order(1)
    fun permissionTest() {
        val uuid = UUID.randomUUID()
        val createdAt = LocalDateTime.now()
        val updatedAt = LocalDateTime.now()
        val name = "test"
        val permission = Permission().apply {
            this.name = name
            this.id = uuid
            this.lastModifiedDate = updatedAt
            this.createdDate = createdAt
        }
        println(permission.toString())
        assertEquals(uuid, permission.id)
        assertEquals(name, permission.name)
        assertEquals(createdAt, permission.createdDate)
        assertEquals(updatedAt, permission.lastModifiedDate)
    }

    @Test
    @Order(2)
    fun roleTest() {
        val name = "ROLE_TEST"
        val uuid = UUID.randomUUID()
        val createdAt = LocalDateTime.now()
        val updatedAt = LocalDateTime.now()

        val role1 = Role().apply {
            this.name = name
            this.id = uuid
            this.lastModifiedDate = updatedAt
            this.createdDate = createdAt
        }

        assertEquals(name, role1.name)
        assertEquals(uuid, role1.id)
        assertEquals(updatedAt, role1.lastModifiedDate)
        assertEquals(createdAt, role1.createdDate)
    }

    @Test
    @Order(3)
    fun personTest() {
        val birthDate = LocalDate.now()
        val firstName = "Test"
        val lastName = "Test"
        val position = "Test"


        val person = Person().apply {
            this.firstName = firstName
            this.lastName = lastName
            this.birthDate = birthDate
            this.position = position
        }

        assertEquals(birthDate, person.birthDate)
        assertEquals(lastName, person.lastName)
        assertEquals(firstName, person.firstName)
        assertEquals(position, person.position)
    }
}