package com.gmail.maystruks08.domain.interactors

import com.gmail.maystruks08.domain.entities.ResultOfTask
import com.gmail.maystruks08.domain.repository.DefaultRepository
import com.gmail.maystruks08.domain.util.LogHelper
import javax.inject.Inject

class DefaultInteractorImpl @Inject constructor(
    private val defaultRepository: DefaultRepository,
    private val logHelper: LogHelper
) : DefaultInteractor {


    override suspend fun doAnyActionUseCase(): ResultOfTask<Exception, Unit> {
        TODO("Not yet implemented")
    }

}