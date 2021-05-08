package com.example.databaseapp.database

class RegisterRepository(private val dao: RegisterDatabaseDao) {

    val users = dao.getAllUsers()
    suspend fun insert(user: RegisterEntity) {
        return dao.insert(user)
    }

    suspend fun getUserName(userName: String):RegisterEntity?{

        return dao.getUsername(userName)
    }
    suspend fun update(user: RegisterEntity){
        return dao.update(user)
    }
    //suspend fun deleteAll(): Int {
    //    return dao.deleteAll()
    //}

}