package com.gmail.maystruks08.domain.interactors

import com.gmail.maystruks08.domain.entities.ResultOfTask

interface DefaultInteractor {

    suspend fun doAnyActionUseCase(): ResultOfTask<Exception, Unit>

}