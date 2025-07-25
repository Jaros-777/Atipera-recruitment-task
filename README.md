# Atipera Recruitment Task - GitHub Repositories API

## Opis projektu

Aplikacja REST API napisana w Javie 21 i Spring Boot 3.5, która udostępnia endpoint umożliwiający pobranie listy publicznych repozytoriów danego użytkownika GitHub, spełniając następujące kryteria:

- Zwracane są tylko repozytoria **nie będące forkami**.
- Dla każdego repozytorium zwracane są:
  - Nazwa repozytorium (`name`).
  - Login właściciela (`owner.login`).
  - Lista gałęzi (`branch`) z nazwą i ostatnim sha commita.
- Obsługa błędu 404, gdy użytkownik GitHub nie istnieje, z czytelnym komunikatem.

Projekt wykorzystuje oficjalne GitHub REST API v3 ([https://developer.github.com/v3](https://developer.github.com/v3)) jako źródło danych.

---

## Technologie

- Java 21
- Spring Boot 3.5
- Spring MVC (bez WebFlux)
- Spring Test + MockMvc dla testów integracyjnych
- Maven jako system budowania
- Lombok dla wygody w modelach danych

---

## Uruchomienie aplikacji

1. Sklonuj repozytorium:

   ```
   git clone https://github.com/Jaros-777/Atipera-recruitment-task.git
   cd Atipera-recruitment-task
   ```
2. Zbuduj projekt:
   ```
   mvn clean install
    ```
4. Uruchom aplikację:
   ```
   mvn spring-boot:run
   ```
## Dostępne endpointy
1. Pobierz listę repozytoriów użytkownika GitHub
   ```
   GET /repo/{username}
   ```
Parametry
- username - login użytkownika GitHub
   Przykład odpowiedzi
    
   ```
   [
    {
      "name": "ATM",
      "owner": {
        "login": "Jaros-777"
      },
      "branch": [
      {
    "name": "main",
    "commit": {
    "sha": "6ad1232d7701a761266315a3343ab8f6fc5281c2"
           }
         }
       ]
     }
   ]
    ```
## Obsługa błędów
Jeśli użytkownik o podanym loginie nie istnieje, serwer zwróci status 404 oraz odpowiedź w formacie:
```
{
  "status": 404,
  "message": "User not found"
}
```
## Testy
Projekt zawiera jeden test integracyjny (bez mocków), który weryfikuje happy path — czyli prawidłowe pobranie repozytorium wraz z danymi gałęzi i właściciela.

Test jest napisany przy użyciu MockMvc i uruchamiany w kontekście Spring Boot.

## Uwagi
- Projekt celowo nie używa WebFlux ani zaawansowanych architektur typu DDD czy Hexagonal.
- Brak paginacji zgodnie z wymaganiami zadania.
- Kod stawia na czytelność i zgodność z branżowymi standardami.

## Licencja
Projekt udostępniony na licencji MIT.


   
