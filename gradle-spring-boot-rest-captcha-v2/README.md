### Proyecto Prueba de back-end  para validar el token ReCAPTCHA desde sitio web
* Descripcion
    * Rest que  valida el token a google.
* Dependencia
    * Proyecto Back-End: node-front-react-captcha-v2
    * Descripcion: Tiene el ReCAPTCHA-v2 y envia token a ser validado al back-end

### Instrucciones
1. Ir a https://www.google.com/recaptcha/admin/create
   1. Acceder en cuenta gmail
   2. Datos (Modo desarrollo)
      1. Etiqueta: localhost
      2. Tipo de reCAPTCHA: Casilla No soy un robot
      3. Dominios: localhost
      4. Propietarios: <correos>
      5. =>aceptar condiciones
      6. Boton 'enviar'
   3. Tras enviar se generaran 2 claves (sitekey,secretkey)
      1. sitekey: Implementar en sitio web
      2. secretkey: Implementar en back-end

### Notas:
* https://developers.google.com/recaptcha/docs/verify (Obtencion de API Request google)

[//]: # (* https://mkyong.com/java8/java-8-how-to-format-localdatetime/ &#40;Datetime&#41;)
[//]: # (* https://www.baeldung.com/spring-resttemplate-post-json &#40;resttemplate&#41;)
[//]: # (* https://www.youtube.com/watch?v=HUlLj6Do1Cs &#40;How to Integrate Google reCAPTCHA v2 in Spring Boot&#41; &#40;Me sirvio para sacar la idea central&#41;)
