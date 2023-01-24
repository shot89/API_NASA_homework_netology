# Чтение данных API NASA

## Описание
Нужно воспользоваться публичным API NASA и скачать ежедневно выгружаемые им изображение или другой контент (например видео). 
Несмотря на то, что API публичный, доступ к нему предоставляется по ключу, который достаточно просто получить по адресу: https://api.nasa.gov/.

Перейдя по ссылке, заполняем личными данными поля: First Name, Last Name, Email и в ответ (а так же на почтовый адрес) будет
выслан ключ. С этим ключом нужно делать запросы к API. 

Итак, чтобы получить ссылку на картинку или другой контент, нужно:
1. Сделать запрос по адресу: https://api.nasa.gov/planetary/apod?api_key=ВАШ_КЛЮЧ
2. Разобрать полученный ответ
3. В ответе найти поле `url` - оно содержит адрес на изображение или другой контент (например видео), который нужно скачать и сохранить локально (на своем компьютере), имя сохраняемого файла нужно взять из части url (из примера ниже DSC1028_PetersNEOWISEAuroralSpike_800.jpg)
4. Проверить что сохраненный файл открывается.

Пример ответа сервиса NASA
```json
{
  "copyright": "Bill Peters",
  "date": "2020-07-17",
  "explanation": "After local midnight on July 14 comet NEOWISE was still above the horizon for Goldenrod, Alberta, Canada, just north of Calgary, planet Earth. In this snapshot it makes for an awesome night with dancing displays of the northern lights. The long-tailed comet and auroral displays are beautiful apparitions in the north these days. Both show the influence of spaceweather and the wind from the Sun. Skygazers have widely welcomed the visitor from the Oort cloud, though C/2020 F3 (NEOWISE) is in an orbit that is now taking it out of the inner Solar System.  Comet NEOWISE Images: July 16 | July 15 | July 14 | July 13 | July 12 | July 11 | July 10 & earlier",
  "hdurl": "https://apod.nasa.gov/apod/image/2007/DSC1028_PetersNEOWISEAuroralSpike.jpg",
  "media_type": "image",
  "service_version": "v1",
  "title": "NEOWISE of the North",
  "url": "https://apod.nasa.gov/apod/image/2007/DSC1028_PetersNEOWISEAuroralSpike_800.jpg"
}
```

## Что нужно сделать
1. Получить ключ для API NASA по адресу: https://api.nasa.gov/
2. Сделать запрос из кода: https://api.nasa.gov/planetary/apod?api_key=ВАШ_КЛЮЧ
3. Создать класс ответа и разобрать json-ответ с помощью Jackson или Gson
4. Найти поле url в ответе и скачать массив byte, который сохранить в файл
5. Имя файла должно быть взято из части url