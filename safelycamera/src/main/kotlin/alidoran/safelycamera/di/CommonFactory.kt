package alidoran.safelycamera.di

import alidoran.safelycamera.tools.Common
import dagger.Component

@Component
interface CommonFactory {
    fun commonProvider(): Common
}