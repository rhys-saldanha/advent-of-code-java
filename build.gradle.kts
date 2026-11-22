plugins {
	java
	id("org.springframework.boot") version "4.2.0"
	id("io.spring.dependency-management") version "1.1.7"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(25)
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter")
	implementation("io.projectreactor:reactor-core")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("io.projectreactor:reactor-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.register("newDay") {
	group = "advent of code"
	description = "Scaffold a new day: ./gradlew newDay -Pyear=2022 -Pday=2"

	val yearProperty = providers.gradleProperty("year")
	val dayProperty = providers.gradleProperty("day")

	doLast {
		val year = yearProperty.orNull ?: throw GradleException("Specify -Pyear=YYYY")
		val day = dayProperty.orNull?.toIntOrNull()?.takeIf { it in 1..25 }
			?: throw GradleException("Specify -Pday=N with N between 1 and 25")
		val paddedDay = "%02d".format(day)

		val javaDir = project.layout.projectDirectory.dir("src/main/java/aoc/y$year/d$paddedDay").asFile
		val testJavaDir = project.layout.projectDirectory.dir("src/test/java/aoc/y$year/d$paddedDay").asFile
		val testResourcesDir = project.layout.projectDirectory.dir("src/test/resources/y$year/d$paddedDay").asFile
		val mainResourcesDir = project.layout.projectDirectory.dir("src/main/resources/y$year/d$paddedDay").asFile

		val stub = javaDir.resolve("Day$paddedDay.java")
		val test = testJavaDir.resolve("Day${paddedDay}Test.java")
		val example = testResourcesDir.resolve("example.txt")
		val input = mainResourcesDir.resolve("input.txt")
		listOf(stub, test, example, input).forEach { file ->
			if (file.exists()) throw GradleException("Already exists: ${file.relativeTo(project.rootDir)}")
		}

		listOf(javaDir, testJavaDir, testResourcesDir, mainResourcesDir).forEach { it.mkdirs() }
		stub.writeText(
			"""
			package aoc.y$year.d$paddedDay;

			import aoc.utils.Puzzle;
			import aoc.utils.PuzzleRunner;
			import org.springframework.stereotype.Component;

			@Component("y${year}D$paddedDay")
			public class Day$paddedDay implements Puzzle {

			    public static void main(String[] args) {
			        PuzzleRunner.run(Day$paddedDay.class);
			    }
			}
			""".trimIndent() + "\n"
		)
		test.writeText(
			"""
			package aoc.y$year.d$paddedDay;

			import aoc.utils.TestPuzzleRunner;
			import org.junit.jupiter.api.Test;
			import reactor.test.StepVerifier;

			class Day${paddedDay}Test {

			    @Test
			    void part1() {
			        // TODO: StepVerifier.create(TestPuzzleRunner.part1(Day$paddedDay.class)).expectNext("answer").verifyComplete();
			    }
			}
			""".trimIndent() + "\n"
		)
		example.writeText("")
		input.writeText("")

		println("Scaffolded $year day $day")
	}
}

tasks.register<JavaExec>("runDay") {
	group = "advent of code"
	description = "Run a day's main: ./gradlew runDay -Pyear=2022 -Pday=1"

	val yearProperty = providers.gradleProperty("year")
	val dayProperty = providers.gradleProperty("day")

	classpath = sourceSets["main"].runtimeClasspath
	mainClass.set(providers.provider {
		val year = yearProperty.orNull ?: throw GradleException("Specify -Pyear=YYYY")
		val day = dayProperty.orNull?.toIntOrNull()?.takeIf { it in 1..25 }
			?: throw GradleException("Specify -Pday=N with N between 1 and 25")
		val paddedDay = "%02d".format(day)
		"aoc.y$year.d$paddedDay.Day$paddedDay"
	})
}
