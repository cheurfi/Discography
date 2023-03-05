package di

import com.cheurfi.discography.MainActivity
import dagger.Component
import network.NetworkModule

@Component(modules = [NetworkModule::class])
interface ApplicationComponent {
    fun inject(activity: MainActivity)
}
