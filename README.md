<h1 align="center">Java, SpringSecurity, JWT e MySQL</h1>

https://github.com/user-attachments/assets/0f7912ba-90cd-41cf-ac75-6b18a458feb9

``POST`` - http://localhost:8080/api/registro
````json
{
    "nome": "Eduardo",
    "email": "eduardo.augustopp@hotmail.com",
    "senha": "admin123"
}
````
    {
        "id": 5,
        "nome": "Eduardo",
        "email": "eduardo.augustopp@hotmail.com",
        "senha": "admin123"
    }
---
``POST`` - http://localhost:8080/api/login
````json
{
    "email": "eduardo.augustopp@hotmail.com",
    "senha": "admin123"
}
````
    {
        "token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJlZHVhcmRvLmF1Z3VzdG9wcEBob3RtYWlsLmNvbSIsImV4cCI6MTczODg0Mzc4MX0.kkHbF5EQDiffV8nQreo3jMqq5GSixNTG138ipV7DlXD11u8dDHPpc6nmZMRJFXjP06UWHLXCMX6XQOGWhUVnMg"
    }
---
``GET`` - http://localhost:8080/usuarios/eduardo.augustopp@hotmail.com

    {
        "id": 5,
        "nome": "Eduardo",
        "email": "eduardo.augustopp@hotmail.com",
        "senha": "admin123"
    }
---
``DELETE`` -  http://localhost:8080/usuarios/eduardo.augustopp@hotmail.com

<div>
  <img src="https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/vscode/vscode-original-wordmark.svg" width="40" height="40"/>
  <img src="https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/java/java-original-wordmark.svg" width="40" height="40"/>     
 <img src="https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/spring/spring-original-wordmark.svg" width="40" height="40"/>
 <img src="https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/docker/docker-original-wordmark.svg" width="40" height="40"/>
 <img src="https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/mysql/mysql-original-wordmark.svg" width="40" height="40"/>     
</div>
