# Serializable / Parcelable

- 액티비티에서 다른 액티비티로 클래스 데이터를 전달하기 위해 사용
- 클래스를 직렬화여 인텐트에 추가하여 전달



## Serializable

- 표준 Java 인터페이스
- 해당 클래스가 직렬화 대상이라고 알려주고 어떠한 메서드를 가지지 않는 마커 인터페이스
- 쉽게 사용할 수 있다.
- **Refection을 사용**하기 때문에 **많은 추가 객체를 생성**한다.
  이렇게 생성된 추가 객체들은 가비지 컬렉터의 타겟이 되고 가비지 컬렉션 동작으로 인해 **성능 저하** 및 배터리 소모가 발생한다.



## Parcelable

- Android SDK 인터페이스
- Refection을 사용하지 않게 설계되어있다.
- 직렬화 처리 방법을 사용자가 명시적으로 작성하기 때문에 Refection이 필요없다.
- 구현 해야 하는 필수 메서드를 포함하기 때문에 **보일러 플레이트 코드가 추가된다.**
- Parcelize 어노테이션을 통해 필수 메소드를 자동으로 구현해 줄 수 있다.

