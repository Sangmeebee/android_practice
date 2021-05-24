# Annotation class

- Kotlin / Android에 내장되어있는 built in annotation
- Annotation에 대한 정보를 나타내기 위한 어노테이션인 meta annotation
- 개발자가 직접 만드는 custom annotation
  - Using Reflection : 런타임시 실행중인 class, function, property 를 참조
  - Using Code Generation



### Built In Annotation

- @Deprecated("") 
  - 특정 함수, 클래스, 필드, 생성자 등에 달아 더이상 사용하지 말라는 경고를 주기위한 용도
- @IntRange(from=0, to=100)
  - 특정 함수, 파라미터, 필드 등에 달아 Int Value의 범위를 제한
- @JvmOverloads
  - 함수 또는 생성자 파라미터에 default value가 설정되어 있을 경우 Kotlin Compiler가 default value 만큼의 오버로딩 함수를 만들어주는 Annotation



### Meta Annotation

~~~kotlin
@Target(AnnotationTarget.FUNCTION, AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@Repeatable
@MustBeDocumented
annotation class TestAnnotation
~~~

- @Target
  - Annotation이 어디에 사용 가능한지를 제한하는데 사용
  - 종류
    - CLASS : class, interface, object, annotation class에 사용가능하도록 제한
    - FUCTION : 생성자를 제외한 함수들에 사용 가능하도록 제한
    - FIELD : field들에만 사용 가능하도록 제한
    - TYPE : 모든곳에 사용가능하도록 제한
- @Retention
  - Annotation의 Scope를 제한하는데 사용
  - 종류
    - SOURCE
      - **compile time 에만** 유용하며 빌드된 binary에는 포함되지 않는다
      - 개발중에 warning이 뜨는 걸 보이지 않도록 하는 @suppress 와 같이 개발 중에만 유용하고, binary에 포함될 필요는 없는 경우에 사용
    - BINARY
      - **compile time과 binary에도 포함**되지만 **reflection을 통해 접근할 수는 없다.**
    - RUNTIME
      - **compile time과 binary에도 포함되고, reflection을 통해 접근 가능**
- @Repeatable
  - 한 요소에 Annotation이 중복으로 사용될 수 있는지를 나타냄
- @MustBeDocumented
  - 주로 Library를 만들 때 사용



### Custom Annotation

#### Using Reflection

#### Using Code Generation

