package br.com.douglasmotta.data.mapper

import br.com.douglasmotta.data.network.IMAGE_BASE_URL
import br.com.douglasmotta.data.network.model.CastMemberResponse
import br.com.douglasmotta.domain.model.CastMember
import br.com.douglasmotta.domain.model.ImageSize

fun CastMemberResponse.toModel() = CastMember(
    id = this.id,
    mainRole = this.knownForDepartment,
    name = this.name,
    character = this.character,
    profileUrl = "$IMAGE_BASE_URL/${ImageSize.X_SMALL.size}/${this.profilePath}",
)