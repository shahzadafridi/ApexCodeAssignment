package com.apex.codeassesment.data.remote.mapper

import com.apex.codeassesment.data.remote.dto.user.DobDTO
import com.apex.codeassesment.data.remote.dto.user.IdDTO
import com.apex.codeassesment.data.remote.dto.user.InfoDTO
import com.apex.codeassesment.data.remote.dto.user.LocationDTO
import com.apex.codeassesment.data.remote.dto.user.LoginDTO
import com.apex.codeassesment.data.remote.dto.user.NameDTO
import com.apex.codeassesment.data.remote.dto.user.PictureDTO
import com.apex.codeassesment.data.remote.dto.user.RegisteredDTO
import com.apex.codeassesment.data.remote.dto.user.ResultDTO
import com.apex.codeassesment.data.remote.dto.user.UserDTO
import com.apex.codeassesment.model.user.Coordinates
import com.apex.codeassesment.model.user.Dob
import com.apex.codeassesment.model.user.Id
import com.apex.codeassesment.model.user.Info
import com.apex.codeassesment.model.user.Location
import com.apex.codeassesment.model.user.Login
import com.apex.codeassesment.model.user.Name
import com.apex.codeassesment.model.user.Picture
import com.apex.codeassesment.model.user.Registered
import com.apex.codeassesment.model.user.Result
import com.apex.codeassesment.model.user.Street
import com.apex.codeassesment.model.user.Timezone
import com.apex.codeassesment.model.user.User

object UserMapper {

    fun toUser(dto: UserDTO): User {
        return User(
            info = toInfo(dto.infoDto),
            results = toResults(dto.resultDto)
        )
    }

    private fun toInfo(dto: InfoDTO): Info {
        return Info(
            page = dto.page,
            results = dto.results,
            seed = dto.seed,
            version = dto.version
        )
    }

    private fun toResults(dto: List<ResultDTO>): List<Result> {
        return dto.map {
            Result(
                cell = it.cell,
                dob = toDob(it.dobDTO),
                email = it.email,
                gender = it.gender,
                id = toID(it.idDTO),
                location = toLocation(it.locationDto),
                login = toLogin(it.loginDto),
                name = toName(it.nameDto),
                nat = it.nat,
                phone = it.phone,
                picture = toPicture(it.pictureDto),
                registered = toRegistered(it.registeredDto)
            )
        }
    }

    private fun toID(dto: IdDTO): Id {
        return Id(
            name = dto.name,
            value = dto.value
        )
    }

    private fun toLocation(dto: LocationDTO): Location {
        return Location(
            city = dto.city,
            coordinates = Coordinates(
                latitude = dto.coordinatesDTO.latitude,
                longitude = dto.coordinatesDTO.longitude
            ),
            country = dto.country,
            postcode = dto.postcode,
            state = dto.state,
            street = Street(
                name = dto.streetDto.name,
                number = dto.streetDto.number
            ),
            timezone = Timezone(
                description = dto.timezoneDto.description,
                offset = dto.timezoneDto.offset
            )
        )
    }

    private fun toLogin(dto: LoginDTO): Login {
        return Login(
            md5 = dto.md5,
            password = dto.password,
            salt = dto.salt,
            sha1 = dto.sha1,
            sha256 = dto.sha256,
            username = dto.username,
            uuid = dto.uuid
        )
    }

    private fun toName(dto: NameDTO): Name {
        return Name(
            first = dto.first,
            last = dto.last,
            title = dto.title
        )
    }

    private fun toPicture(dto: PictureDTO): Picture {
        return Picture(
            large = dto.large,
            thumbnail = dto.thumbnail,
            medium = dto.medium
        )
    }

    private fun toRegistered(dto: RegisteredDTO): Registered {
        return Registered(
            age = dto.age,
            date = dto.date
        )
    }

    private fun toDob(dto: DobDTO): Dob{
        return Dob(
            age = dto.age,
            date = dto.date
        )
    }
}