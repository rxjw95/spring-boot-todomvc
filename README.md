# spring-boot-todomvc

## 설계 
```java
/*
 * 1. Client -> (JSON to Request Obj) Controller
 * 2. Controller (Ctrl Request Obj to Domain Obj) -> Service Interface # 컨트롤러는 서비스의 내부를 모른다. 이름만 가져다 쓰는 것
 * 3. Service (Domain Obj to Entity) -> Repository and Service (Entity to Domain Obj) <- Repository  # 해당 Service는 도메인 영역의 구현체, (서비스는 레파지토리를 모른다?)
 * 4. Controller (Domain Obj to Ctrl Response Obj) <- Service
 *
 * - 각 레이어별로 사용되는 VO를 달리하고, 레이어 내에서도 요청별/응답별로 역할에 맞게 VO를 생성해서 경계를 선명히 한다.
 *  - 요청마다 필요한 parameter가 다를 수 있고, null을 허용 여부도 다르기 때문에 나누어 주는게 좋다.
 *
 * - 순서에서 볼 수 있듯이 서비스 레이어에서는 데이터 가공을 수행하지 않음 (프레젠테이션와 도메인 단에서만 가공한다. 그 이유가 기억이 안남)
 * - 접근 제어자로 레이어별로 사용할 VO를 구분한다.
 */
 ```
