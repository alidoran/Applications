package alidoran.safelycamera.di

import alidoran.safelycamera.model.FileModelProvider
import dagger.Component

@Component
interface FileModelFactory {
    fun provider(): FileModelProvider
}