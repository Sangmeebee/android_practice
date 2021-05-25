# Room

- SQLite에 추상화 계층을 제공
  - SQLite
    - 소규모 데이터를 관리하고 사용하는데 적합한 내부 관계형 데이터베이스
  - 추상화 : 공통되는 특성을 추출하여 변화시키는 것
  - 추상화를 시킴으로써 코드의 재사용성, 가독성을 높혀준다.
- 앱을 실행하는 기기에서 데이터의 캐시를 만들 수 있다.
- 인터넷 연결 여부와 관계없이 컨텐츠를 사용자에게 컨텐츠를 제공해줄 수 있다.



### SQLite에서 추가된 기능

- 컴파일 도중 SQL에 대한 유효성 검사 기능
- 스키마가 변경될 시 자동으로 업데이트 기능
- Java 데이터 객체를 변경하기 위해 별도의 코드없이 **ORM 라이브러리를 통해 매핑 기능**
- LiveData와 RxJava를 위한 Observation 생성 및 동작 기능



### 구성요소

- **Room Database** 

  - 데이터베이스의 전체적인 소유자 역할을 하며 DB를 새롭게 생성하거나 버전을 관리

    ~~~kotlin
    @Database(entites = [User::class], version = 1, exportSchema =false)
    abstract class RoomDatabase : RoomDatabase() {
      
      abstract fun userDao(): UserDao
      
      companion object {
        private var instance : RoomDatabase? = null
        
        //객체생성에 비용이 많이 들기 때문에 싱글톤으로 구현하기를 권장하고 있다.
        @Synchronized
        fun getInstance(context: Context) : RoomDatabase? {
          if(instance == null){
            instance = Room.databaseBuilder(context, RoomDatabase::class.java, "sangmee_database")
            //기기의 기존 데이터베이스를 현재 버전으로 업그레이드하기 위한 이전 경로를 찾을 수 없으면 발생하는 Exception을 발생하지 않게 하기 위해 사용
            .fallbackToDestructiveMigration()
            //메인쓰레드에서 데이터베이스 접근을 허용(원래는 허용하지 않음)
            .allowMainThreadQueries()
            .build()
          }
          return instance
        }
      }
    }
    ~~~

    

- **DAO**(Data Access Object)

  - 데이터베이스에 접근하여 수행할 작업을 메소드 형태로 정의

    ~~~kotlin
    @Dao
    interface BaseDao<T> {
      
      @Insert(onConflict = OnConflictStrategy.REPLACE) //이미 존재하는 Entity가 있다면 insert하는 Entity로 대체하여 충돌방지
      fun insert(entity: T): Completable
      
      @Insert(onConflict = OnConflictStrategy.REPLACE)
      fun insert(entitys: Array[out T]): Completable
      
      @Update
      fun update(entity: T) : Completable
      
      @Delete
      fun delete(entity: T) : Completable
    }
    ~~~

    ~~~
    @Dao
    interface UserDao : BaseDao<User> {
    	
    	@Query("SELECT * FROM user_table WHERE id=:id")
    	fun getUser(id: String?): Single<User>
    }
    ~~~

    

- **ENTITY**

  - 데이터베이스 내의 테이블을 뜻하며 DB에 저장할 데이터 형식을 정의

    ~~~kotlin
    @Entity(tableName = "user_table")
    class User(@field:PrimaryKey
               var id: String,
               @field:ColumnInfo(name="name")
               var name: String,
               var birth: String,
               var gender: String,
               var phoneNumber: String,
               var picture: String?,
               var intro: String?,
               var scope: String,
               var point: Int)
    ~~~

    