Программа рассчитывает заказ и печатает чек в файл и консоль.

Файлы с продуктами и дисконтными картами находятся в папке resourcess.

Так же можно указать пути для файлов с продуктами, дисконтными картами и для сохранения чека.

10-ти процентная скидка предоставляется на все товары, стоимость которых равняется или превышает 4 доллара за штуку и количество которых покупается не менее пяти штук.

Изменить значения можно в классе Constants.

Общая скидка на весь заказ предоставляется при предъявлении валидной дисконтной карты.

Аргументы вводятся следующим образом: -arg value
*    -prod   путь к файлу с продуктами (resources/prod.csv)
*    -card   путь к файлу со дисконтными картами (resources/card.csv)
*    -chtxt  путь к файлу для печати чеко (resources/check.txt)
*    -buy    список товаров к покупке, "id-number" (2-5)
*    -dsc    четырехзначный номер скидочной карты (1234)

Аргументы могут располагаться в свободном порядке.

Значения по умолчанию для путей к файлам беруться в случае, когда не указаны соответствующие аргументы.

Значения путей по умолчанию (которые в скобках) можно изменить в файле Constants.

Программа ловит ошибки:
* при отсутствии файла продуктов или дисконтных карт.
* при веденном неправильного id товара.
* при введении неправильного номера дисконтной карты.

Примеры запуска: 

java CheckRunner -dsc 3421 -chtxt resources/check.txt -prod resources/prod.csv -card resources/card.csv -buy "27-3 36-8 38-1 8-10 9-9 14-2 23-8"

java -jar CashReceipt-1.0-SNAPSHOT.jar -dsc 3421 -chtxt resources/check.txt -prod resources/prod.csv -card resources/card.csv -buy "27-3 36-8 38-1 8-10 9-9 14-2 23-8"

java CheckRunner -dsc 3422 -chtxt resources/check.txt -buy "27-3 36-8 39-1 8-10 9-9 14-2 23-8 14-7 3-23"

java -jar CashReceipt-1.0-SNAPSHOT.jar -dsc 3422 -chtxt resources/check.txt -buy "27-3 36-8 39-1 8-10 9-9 14-2 23-8 14-7 3-23"

java CheckRunner -dsc 1234

java -jar CashReceipt-1.0-SNAPSHOT.jar -dsc 1234

java CheckRunner -dsc 3421 -prod resources/prod.csv -card resources/card.csv -buy "27-3 36-8 39-1 8-10 9-9 14-2 23-8 14-7 3-23 17-8 6-5 45-8 35-12"

java -jar CashReceipt-1.0-SNAPSHOT.jar -dsc 3421 -prod resources/prod.csv -card resources/card.csv -buy "27-3 36-8 39-1 8-10 9-9 14-2 23-8 14-7 3-23 17-8 6-5 45-8 35-12"


**pdf check feature**

Добавлена возможность печати чека в pdf файл.

С помощью gradle plugin скачивается pdf template с заданного URL и сохраняется по указанному адресу на локальном диске.

Далее чек печатается на сохраненном pdf template.

Подключить плагин можно следующим образом:

```gradle
plugins {
    id 'ru.clevertec.file-downloader'
}
```

Опции, установленные по умолчанию, которые можно изменить:

```gradle
source{
    pdfTemplate = 'https://github.com/stebadmitriy/files/raw/main/Clevertec_Template.pdf'
    target = "resources/templatesssss.pdf"
}
```

Скачивание происходит при запуске gradle task "downloadPdf"
