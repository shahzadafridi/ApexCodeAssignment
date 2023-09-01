package com.apex.codeassesment

import com.apex.codeassesment.data.remote.dto.user.CoordinatesDTO
import com.apex.codeassesment.data.remote.dto.user.DobDTO
import com.apex.codeassesment.data.remote.dto.user.IdDTO
import com.apex.codeassesment.data.remote.dto.user.InfoDTO
import com.apex.codeassesment.data.remote.dto.user.LocationDTO
import com.apex.codeassesment.data.remote.dto.user.LoginDTO
import com.apex.codeassesment.data.remote.dto.user.NameDTO
import com.apex.codeassesment.data.remote.dto.user.PictureDTO
import com.apex.codeassesment.data.remote.dto.user.RegisteredDTO
import com.apex.codeassesment.data.remote.dto.user.StreetDTO
import com.apex.codeassesment.data.remote.dto.user.TimezoneDTO
import com.apex.codeassesment.data.remote.dto.user.UserDTO
import com.apex.codeassesment.data.remote.dto.user.UserResponseDTO


object FakeData {
    val userResponseDTO = UserResponseDTO(
        infoDto = InfoDTO(
            page = 1,
            results = 1,
            version = "1.4",
            seed = "0b7047ea36987efb"
        ),
        userDto = arrayListOf<UserDTO>().apply {
            add(
                UserDTO(
                    cell = "(330)-023-2503",
                    phone = "(330)-023-2503",
                    dobDTO = DobDTO(
                        date = "1988-03-27T05:15:40.060Z",
                        age = 18
                    ),
                    email = "coskun.abanuz@example.com",
                    gender = "Male",
                    idDTO = IdDTO(
                        name = "fdsaff",
                        value = null
                    ),
                    locationDto = LocationDTO(
                        city = "Bursa",
                        country = "Turkey",
                        postcode = "233242",
                        coordinatesDTO = CoordinatesDTO(
                            latitude = "68.3194",
                            longitude = "-93.3766"
                        ),
                        streetDto = StreetDTO(
                            name = "Mcka Cd",
                            number = 3242
                        ),
                        state = "Tekirdağ",
                        timezoneDto = TimezoneDTO(
                            description = "Atlantic Time (Canada), Caracas, La Paz",
                            offset = "-4:00"
                        )
                    ),
                    loginDto = LoginDTO(
                        md5 = "08b793a25502fe2d8f8871dfb75467c7",
                        sha1 = "c8231d8361c1300a1985a4cc4c55d646fd5b960c",
                        sha256 = "de9f42b2159b55aeac53cb80f499f33a1f4eb439816864daca71cdc4e69c7731",
                        salt = "WCao6esD",
                        username = "asd123",
                        uuid = "2045b3b2-c703-4e14-b1ee-cc25ece38d25",
                        password = "234234243"
                    ),
                    nameDto = NameDTO(
                        first = "joh",
                        last = "doe",
                        title = "Jon doe"
                    ),
                    nat = "dfafdasf",
                    pictureDto = PictureDTO(
                        large = "https://randomuser.me/api/portraits/men/34.jpg",
                        medium = "https://randomuser.me/api/portraits/men/34.jpg",
                        thumbnail = "https://randomuser.me/api/portraits/men/34.jpg"
                    ),
                    registeredDto = RegisteredDTO(
                        age = 29,
                        date = "2005-07-19T02:47:27.956Z"
                    )

                )
            )
            add(
                UserDTO(
                    cell = "(330)-023-2503",
                    phone = "(330)-023-2503",
                    dobDTO = DobDTO(
                        date = "1988-03-27T05:15:40.060Z",
                        age = 18
                    ),
                    email = "coskun.abanuz@example.com",
                    gender = "Male",
                    idDTO = IdDTO(
                        name = "fdsaff",
                        value = null
                    ),
                    locationDto = LocationDTO(
                        city = "Bursa",
                        country = "Turkey",
                        postcode = "233242",
                        coordinatesDTO = CoordinatesDTO(
                            latitude = "68.3194",
                            longitude = "-93.3766"
                        ),
                        streetDto = StreetDTO(
                            name = "Mcka Cd",
                            number = 3242
                        ),
                        state = "Tekirdağ",
                        timezoneDto = TimezoneDTO(
                            description = "Atlantic Time (Canada), Caracas, La Paz",
                            offset = "-4:00"
                        )
                    ),
                    loginDto = LoginDTO(
                        md5 = "08b793a25502fe2d8f8871dfb75467c7",
                        sha1 = "c8231d8361c1300a1985a4cc4c55d646fd5b960c",
                        sha256 = "de9f42b2159b55aeac53cb80f499f33a1f4eb439816864daca71cdc4e69c7731",
                        salt = "WCao6esD",
                        username = "asd123",
                        uuid = "2045b3b2-c703-4e14-b1ee-cc25ece38d25",
                        password = "234234243"
                    ),
                    nameDto = NameDTO(
                        first = "joh",
                        last = "doe",
                        title = "Jon doe"
                    ),
                    nat = "dfafdasf",
                    pictureDto = PictureDTO(
                        large = "https://randomuser.me/api/portraits/men/34.jpg",
                        medium = "https://randomuser.me/api/portraits/men/34.jpg",
                        thumbnail = "https://randomuser.me/api/portraits/men/34.jpg"
                    ),
                    registeredDto = RegisteredDTO(
                        age = 29,
                        date = "2005-07-19T02:47:27.956Z"
                    )

                )
            )
            add(
                UserDTO(
                    cell = "(330)-023-2503",
                    phone = "(330)-023-2503",
                    dobDTO = DobDTO(
                        date = "1988-03-27T05:15:40.060Z",
                        age = 18
                    ),
                    email = "coskun.abanuz@example.com",
                    gender = "Male",
                    idDTO = IdDTO(
                        name = "fdsaff",
                        value = null
                    ),
                    locationDto = LocationDTO(
                        city = "Bursa",
                        country = "Turkey",
                        postcode = "233242",
                        coordinatesDTO = CoordinatesDTO(
                            latitude = "68.3194",
                            longitude = "-93.3766"
                        ),
                        streetDto = StreetDTO(
                            name = "Mcka Cd",
                            number = 3242
                        ),
                        state = "Tekirdağ",
                        timezoneDto = TimezoneDTO(
                            description = "Atlantic Time (Canada), Caracas, La Paz",
                            offset = "-4:00"
                        )
                    ),
                    loginDto = LoginDTO(
                        md5 = "08b793a25502fe2d8f8871dfb75467c7",
                        sha1 = "c8231d8361c1300a1985a4cc4c55d646fd5b960c",
                        sha256 = "de9f42b2159b55aeac53cb80f499f33a1f4eb439816864daca71cdc4e69c7731",
                        salt = "WCao6esD",
                        username = "asd123",
                        uuid = "2045b3b2-c703-4e14-b1ee-cc25ece38d25",
                        password = "234234243"
                    ),
                    nameDto = NameDTO(
                        first = "joh",
                        last = "doe",
                        title = "Jon doe"
                    ),
                    nat = "dfafdasf",
                    pictureDto = PictureDTO(
                        large = "https://randomuser.me/api/portraits/men/34.jpg",
                        medium = "https://randomuser.me/api/portraits/men/34.jpg",
                        thumbnail = "https://randomuser.me/api/portraits/men/34.jpg"
                    ),
                    registeredDto = RegisteredDTO(
                        age = 29,
                        date = "2005-07-19T02:47:27.956Z"
                    )

                )
            )
        }
    )
}
