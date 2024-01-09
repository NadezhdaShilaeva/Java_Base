# Java Base
###### Made on [the course from Stepik](https://stepik.org/course/187/info)


#### Файл AsciiCharSequence

Класс ***AsciiCharSequence***, реализующий компактное хранение последовательности ***ASCII***-символов (их коды влезают в один байт) 
в массиве байт. По сравнению с классом ***String***, хранящим каждый символ как ***char***, *AsciiCharSequence* занимает в два 
раза меньше памяти.

Класс *AsciiCharSequence*:

- реализует интерфейс ***java.lang.CharSequence***;
- имеет конструктор, принимающий массив байт;
- определяет методы ***length(), charAt(), subSequence() и toString()***

---

#### Файл ComplexNumber
Класс ***ComplexNumber*** реализующий комплксное число. В классе переопределены методы ***equals()*** и ***hashCode()*** так, 
что ***equals()*** сравнивает экземпляры *ComplexNumber* по содержимому полей ***re*** и ***im*** (действительная и мнимая части), а ***hashCode()***
согласован с реализацией ***equals()***.

---

#### Директория **oop**

Система фильтрации комментариев на каком-то веб-портале, будь то новости, видео-хостинг, а может даже для системы онлайн-обучения.

Программа фильтрует спам, комментарии с негативным содержанием и слишком длинные комментарии.
Спам фильтруется по наличию указанных ключевых слов в тексте.
Негативное содержание определяется по наличию одного из трех смайликов – :( =( :|
Слишком длинные комментарии определяются исходя из данного числа – максимальной длины комментария.

Фильтр абстрагирован в виде интерфейса ***TextAnalyzer***.

***Label*** – тип-перечисление, которые содержит метки, которыми помечается текст:
***SPAM, NEGATIVE_TEXT, TOO_LONG, OK***

Реализовано три класса, которые реализуют данный интерфейс: ***SpamAnalyzer, NegativeTextAnalyzer и TooLongTextAnalyzer***.

- ***SpamAnalyzer*** конструируется от массива строк с ключевыми словами. Объект этого класса сохраняет в своем состоянии этот массив строк в приватном поле *keywords*.
- ***NegativeTextAnalyzer*** конструируется конструктором по-умолчанию.
- ***TooLongTextAnalyzer*** конструируется от int'а с максимальной допустимой длиной комментария. Объект этого класса сохраняет в своем состоянии это число в приватном поле *maxLength*.

Общая логика классов ***SpamAnalyzer*** и ***NegativeTextAnalyzer*** абстрагирована в абстрактный класс KeywordAnalyzer следующим образом:
- Выделено два абстрактных метода ***getKeywords*** и ***getLabel***, один из которых будет возвращать набор ключевых слов, а второй метку, которой необходимо пометить положительные срабатывания.
- ***processText*** реализован таким образом, что он зависит только от *getKeywords* и *getLabel*.
- ***SpamAnalyzer*** и ***NegativeTextAnalyzer*** являются наследниками ***KeywordAnalyzer*** и реализуют абстрактные методы.

Метод ***checkLabels*** возвращает метку для комментария по набору анализаторов текста. *checkLabels* возвращает первую ***не-OK*** метку в порядке данного набора анализаторов, и ***OK***, если все анализаторы вернули ***OK***.

---

#### Файл TestCallerClassAndMethodName

Реализует метод, позволяющий другим методам узнать, откуда их вызвали.

Метод ***getCallerClassAndMethodName()*** возвращает имя класса и метода, откуда вызван метод, вызвавший данный утилитный метод. Или ***null***, если метод, вызвавший *getCallerClassAndMethodName()* является точкой входа в программу, т.е. его никто не вызывал.

Это актуально, например, в библиотеках логирования, где для каждого сообщения в логе надо выводить класс и метод, откуда сообщение было залогировано.

---

#### Файл LoggingConfig

В классе реализован метод, настраивающий параметры логирования.

Конфигурируются такие настройки, что:

- Логгер с именем ***"org.stepic.java.logging.ClassA"*** принимал сообщения всех уровней (***ALL***).
- Логгер с именем ***"org.stepic.java.logging.ClassB"*** принимал только сообщения уровня ***WARNING и серьезнее***.
- Все сообщения, пришедшие от нижестоящих логгеров на уровень ***"org.stepic.java"***, независимо от серьезности сообщения печатаются в консоль в формате XML и не передаются вышестоящим обработчикам на уровнях *"org.stepic", "org" и ""*.

---

#### Директория postOfficeSystem

Программа описывает работу гипотетической почтовой системы.

***Sendable*** - интерфейс: сущность, которую можно отправить по почте.
У такой сущности можно получить от кого и кому направляется письмо.

У *Sendable* есть два наследника, объединенные следующим абстрактным классом:

***AbstractSendable*** - абстрактный класс,который позволяет абстрагировать логику хранения
источника и получателя письма в соответствующих полях класса.

***MailMessage*** - письмо, у которого есть текст, который можно получить с помощью метода *getMessage()*.

***MailPackage*** - посылка, содержимое которой можно получить с помощью метода *getContent()*.

***Package*** - класс, который задает посылку. У посылки есть текстовое описание содержимого и целочисленная ценность.

Классы, которые моделируют работу почтового сервиса:

***MailService*** - интерфейс, который задает класс, который может каким-либо образом обработать почтовый объект.

***RealMailService*** - класс, в котором скрыта логика настоящей почты.

***UntrustworthyMailWorker*** – класс, моделирующий ненадежного работника почты, который вместо того, чтобы передать почтовый объект 
непосредственно в сервис почты, последовательно передает этот объект набору третьих лиц, а затем передает получившийся объект непосредственно 
экземпляру *RealMailService*.

***Spy*** – шпион, который логгирует о всей почтовой переписке, которая проходит через его руки. Объект конструируется от экземпляра Logger, с помощью которого шпион будет сообщать о всех действиях. Он следит за объектами класса MailMessage и пишет в логгер следующие сообщения:
   1) Если в качестве отправителя или получателя указан *"Austin Powers"*, то он пишет в лог сообщение с уровнем ***WARN: Detected target mail correspondence: from {from} to {to} "{message}"***
   2) Иначе, он пишет в лог сообщение с уровнем ***INFO: Usual correspondence: from {from} to {to}***

***Thief*** – вор, который ворует самые ценные посылки и игнорирует все остальное. Вор принимает в конструкторе переменную int – минимальную стоимость посылки, которую он будет воровать. Также, в данном классе реализован метод ***getStolenValue***, который возвращает суммарную стоимость всех посылок, которые он своровал. Воровство происходит следующим образом: вместо посылки, которая пришла вору, он отдает новую, такую же, только с нулевой ценностью и содержимым посылки ***"stones instead of {content}"***.

***Inspector*** – инспектор, который следит за запрещенными и украденными посылками и бьет тревогу в виде исключения, если была обнаружена подобная посылка. Если он заметил запрещенную посылку с одним из запрещенных содержимым *("weapons" и "banned substance")*, то он бросает ***IllegalPackageException***. Если он находит посылку, состоящую из камней *("stones")*, то выбрасывается ***StolenPackageException***.

---

#### Директория inputOutput

Содержит файлы с решениями задач курса на ввод-вывод.

* Файл ***ReplacerWindowsToUnix*** - преобразовывает переводы строк из формата Windows в формат Unix. 
Заменяет все вхождения пары символов '\r' и '\n' на один символ '\n'. Если на входе встречается одиночный символ '\r', за которым не следует '\n', то символ '\r' выводится без изменения.
* Файл ***DoublesAdder*** - программа, читающая текст из ***System.in*** и выводящая в ***System.out*** сумму всех встреченных в тексте вещественных чисел 
с точностью до шестого знака после запятой (если последовательность непробельных символов не является числом (приводимым к Double), то она не учитывается).
* Файл ***Deserializer*** - реализует метод ***deserializeAnimalArray***, который из переданного массива байт восстанавливает массив объектов ***Animal***.
Если вдруг массив байт не является корректным представлением массива экземпляров Animal, то метод бросает исключение ***java.lang.IllegalArgumentException***.

---

#### Файл Pair

Реализует generic-класс *Pair*, содержащий пару элементов разных типов и не запрещающий элементам принимать значение *null*.

В классе реализованы методы ***getFirst(), getSecond(), equals() и hashCode()***, а также статический фабричный метод ***of()***.

---

#### Директория mailService

В программе реализован почтовый сервис по отправке писем и зарплат.

***Sendable*** - интерфейс: сущность, которую можно отправить по почте.
У такой сущности можно получить от кого и кому направляется письмо.

У *Sendable* есть два наследника:

***MailMessage*** - письмо, у которого есть текст, который можно получить с помощью метода *getContent()*.

***Salary*** - зарплата, сумму которой можно получить с помощью метода *getContent()*.

***MailService*** - класс, который моделируют работу почтового сервиса. 
Имплементирует функциональный интерфейс ***Consumer<Sendable<T>>*** для упрощенной обработки коллекций с посылаемыми объектами.

Вся реализация сервиса соответсвует требованиям задания, обозначенным в файле ***Main*** (который корректно отрабатывет при запуске).
