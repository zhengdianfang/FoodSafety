package com.zhengdianfang.foodsafety.profile.repository

import com.zhengdianfang.foodsafety.common.model.User
import com.zhengdianfang.foodsafety.profile.datasource.api.UserApi
import com.zhengdianfang.foodsafety.profile.datasource.database.UserDao
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@RunWith(MockitoJUnitRunner::class)
class UserRepositoryTest {

    @JvmField
    @Rule
    val mockServer = MockWebServer()

    @Mock
    private lateinit var userDao: UserDao

    private lateinit var userRepository: UserRepository

    @Before
    fun setUp() {
        val client = Retrofit.Builder()
                .baseUrl(mockServer.url("/"))
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        this.userRepository = UserRepository(client.create(UserApi::class.java), userDao)
    }

    @Test
    fun testLogin() {
        mockServer.enqueue(MockResponse().setResponseCode(200).setBody("{\"id\": \"1\", \"username\": \"zdf\", \"nickname\": \"xx\"}"))
        `when`(userDao.getUser(1)).thenReturn(User(1, "zdf", "xx"))
        this.userRepository.login("zdf", "111111") { user ->
            assertNotNull(user)
            assertEquals(2, user?.id)
            assertEquals("zdf111", user?.username)
        }
        Thread.sleep(50000)
    }
}
