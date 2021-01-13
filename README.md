# TimeTable

TimeTable is a **Discord BOT** that create you **a calendar inside a Discord Server**. That could be useful to list all your incoming events.
*I'm using it for my Discord school to list all incoming exams.*

### Tech

TimeTable uses a number of open source projects to work properly:

* [JDA](https://github.com/DV8FromTheWorld/JDA) - Awesome Java Discord API
* [GSON](https://github.com/google/gson) - Google JSON Parser
* [HikariCP](https://github.com/brettwooldridge/HikariCP) - JDBC connection pool
* [Project Reactor](https://projectreactor.io/) - Non-Blocking Reactive Streams

### Installation

**TimeTable** requires [Java](https://www.java.com/fr/) 8+ and [Maven](https://maven.apache.org/) to run.

I recommand to have **[Docker](https://www.docker.com/)** to have a **easier installation**.

- Set **according environments variable** in the docker-compose.yml
- Build the Java App

```sh
$ cd timetable
$ mvn package
$ mv target/*.jar data/
$ docker-compose up -d
```

### Development

Want to contribute? Great!

Just make a pull request and if it's great i will merge it !

### Todos

- Add more commands
- Add command to get through a RestAPI all the notes of the student to send it in private message

License
----

MIT
**Free Software, Hell Yeah!**