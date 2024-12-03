/**
 * Реализация системы бронирования: Разработайте систему для бронирования мест в отеле.
 * Создайте классы Room, Guest, Reservation, и реализуйте методы для управления бронированием,
 * обработки запросов, изменения брони, и расчета стоимости проживания с учетом дополнительных услуг.
 * */

data class Room(
    val roomNumber: Int,
    val type: String,
    val pricePerNight: Double,
    var isBooked: Boolean = false
)

data class Guest(
    val name: String,
    val email: String
)

class Reservation(
    val guest: Guest,
    val room: Room,
    val startDate: String,
    val endDate: String,
    var additionalServices: List<String> = listOf()
) {
    fun calculateTotalCost(): Double {
        val nights = calculateNights(startDate, endDate)
        val serviceCost = additionalServices.size * 50.0
        return (nights * room.pricePerNight) + serviceCost
    }

    private fun calculateNights(start: String, end: String): Int {
        val startDate = java.time.LocalDate.parse(start)
        val endDate = java.time.LocalDate.parse(end)
        return java.time.Duration.between(startDate.atStartOfDay(), endDate.atStartOfDay()).toDays().toInt()
    }
}

class HotelBookingSystem {
    private val rooms = mutableListOf<Room>()
    private val reservations = mutableListOf<Reservation>()

    fun addRoom(room: Room) {
        rooms.add(room)
    }

    fun bookRoom(guest: Guest, roomNumber: Int, startDate: String, endDate: String): String {
        val room = rooms.find { it.roomNumber == roomNumber && !it.isBooked }
        return if (room != null) {
            val reservation = Reservation(guest, room, startDate, endDate)
            reservations.add(reservation)
            room.isBooked = true
            "Номер успешно забронирован!"
        } else {
            "Номер занят"
        }
    }

    fun modifyReservation(reservation: Reservation, newStartDate: String, newEndDate: String): String {
        cancelReservation(reservation)
        val newReservation = Reservation(reservation.guest, reservation.room, newStartDate, newEndDate)
        reservations.add(newReservation)
        return "Бронирование успешно изменено!"
    }

    fun cancelReservation(reservation: Reservation) {
        reservations.remove(reservation)
        reservation.room.isBooked = false
    }

    fun listReservations(): List<Reservation> {
        return reservations
    }
}

fun main() {
    val hotel = HotelBookingSystem()
    hotel.addRoom(Room(101, "Одиночная", 100.0))
    hotel.addRoom(Room(102, "Двойная", 150.0))
    val guest = Guest("Джон Марстон", "john@example.com")
    println(hotel.bookRoom(guest, 101, "2024-10-01", "2024-10-05"))
    hotel.listReservations().forEach {
        println("Бронировани: ${it.guest.name} в номере ${it.room.roomNumber} с ${it.startDate} до ${it.endDate} с общей стоимостью: ${it.calculateTotalCost()}")
    }
}