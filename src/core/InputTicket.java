package core;

import core.exceptions.CoordinateXException;
import core.exceptions.ValueIsNotPositiveException;
import data.*;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import static core.Globals.COORDINATE_X_MIN_LIMIT;

public class InputTicket {
    private static final Scanner scanner = new Scanner(System.in);
    
    public static Ticket getTicketFromConsole(CollectionManager collectionManager) {
        Ticket ticket = new Ticket();

        setId(ticket, collectionManager);
        setName(ticket);
        setCoordinates(ticket);
        setCreationDate(ticket);
        setPrice(ticket);
        setType(ticket);
        setPerson(ticket);
        return ticket;

    }

    public static void setPerson(Ticket ticket) {
        Person person = new Person();
        System.out.println("Создание человека");

        setPersonsBirthday(person);
        setPersonsEyeColor(person);
        setPersonsHairColor(person);
        setPersonsNationality(person);
        if (!isLocationInputted()) {
            person.setLocation(null);
        } else {
            setLocation(person);
        }

        ticket.setPerson(person);
    }

    public static void setLocation(Person person) {
        Location location = new Location();
        setLocationX(location);
        setLocationY(location);
        setLocationZ(location);

        person.setLocation(location);
    }

    public static void setLocationX(Location location) {
        while (true) {
            try {
                System.out.print("Введите координату x (Person x): ");
                location.setX(Double.parseDouble(scanner.nextLine()));
                break;
            } catch (NumberFormatException e) {
                System.out.println("Введен текст");
            }
        }
    }

    public static void setLocationY(Location location) {
        while (true) {
            try {
                System.out.print("Введите координату y (Person y): ");
                location.setY(Integer.parseInt(scanner.nextLine()));
                break;
            } catch (NumberFormatException e) {
                System.out.println("Введен текст");
            } catch (NullPointerException e) {
                System.out.println("Введен null");
            }
        }
    }

    public static void setLocationZ(Location location) {
        while (true) {
            try {
                System.out.print("Введите координату z (Person z): ");
                location.setZ(Float.parseFloat(scanner.nextLine()));
                break;
            } catch (NumberFormatException e) {
                System.out.println("Введен текст");
            }
        }
    }

    public static boolean isLocationInputted() {
        System.out.println("Хотите ли вы ввести значения локации? \n" +
                "Press enter or input something [no, yes]");
        String line = scanner.nextLine();
        return !line.isEmpty();
    }

    public static void setPersonsNationality(Person person) {
        printCountries();
        while (true) {
            try {
                System.out.print("Национальность (nationality): ");
                person.setNationality(Country.valueOf(scanner.nextLine()));
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Введена неправильная страна");
            }
        }
    }

    public static void printCountries() {
        System.out.println("Доступные страны");
        for (Country country : Country.values()) {
            System.out.println(country);
        }
    }

    public static void setPersonsHairColor(Person person) {
        printColors();
        while (true) {
            try {
                System.out.print("Цвет волос (hairColor): ");
                String stringColor = scanner.nextLine();
                if (stringColor.isEmpty()) {
                    person.setHairColor(null);
                } else {
                    person.setHairColor(Color.valueOf(stringColor));
                }
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Введён неправильный цвет волос");
            }
        }
    }

    public static void setPersonsEyeColor(Person person) {
        printColors();
        while (true) {
            try {
                System.out.print("Цвет глаз (eyeColor): ");
                person.setEyeColor(Color.valueOf(scanner.nextLine()));
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Введён неправильный цвет глаз");
            }
        }
    }

    public static void printColors() {
        System.out.println("Доступные цвета");
        for (Color color : Color.values()) {
            System.out.println(color);
        }
    }

    public static void setPersonsBirthday(Person person) {
        System.out.println("Пример ввода дня рождения: Пример ввода дня рождения: 2007-12-03T10:15:30. ISO FORMAT");
        while (true) {
            try {
                System.out.print("Введите день рождения (birthday): ");
                String stringBirthday = scanner.nextLine();
                if (stringBirthday.isEmpty()) {
                    person.setBirthday(null);
                } else {
                    person.setBirthday(LocalDateTime.parse(stringBirthday, DateTimeFormatter.ISO_LOCAL_DATE_TIME));
                }
                break;
            } catch (DateTimeParseException e) {
                System.out.println("Неправильно введена дата. Пример: 2007-12-03T10:15:30");
            }
        }
    }

    public static void setType(Ticket ticket) {
        printAllTypesOfTickets();

        while (true) {
            try {
                System.out.print("Введите вид бидета (type): ");
                ticket.setType(TicketType.valueOf(scanner.nextLine()));
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Введён неправильный вид билета");
            }
        }
    }

    public static void printAllTypesOfTickets() {
        System.out.println("Виды билетов: ");
        for (TicketType value : TicketType.values()) {
            System.out.println(value);
        }
    }

    public static void setPrice(Ticket ticket) {
        while (true) {
            try {
                System.out.print("Введите цену (price): ");
                String line = scanner.nextLine();
                if (line.isEmpty()) {
                    System.out.println("Цена должна быть положительным числом");
                } else {
                    ticket.setPrice(Long.parseLong(line));
                    break;
                }
            } catch (ValueIsNotPositiveException | NumberFormatException e) {
                System.out.println("Цена должна быть положительным числом");
            }
        }
    }

    public static void setCreationDate(Ticket ticket) {
        ticket.setCreationDate(ZonedDateTime.now());
    }

    public static void setId(Ticket ticket, CollectionManager collectionManager) {
        long id = collectionManager.getNewId();
        ticket.setId(id);
    }

    public static void setName(Ticket ticket) {
        String name = "";
        while (name.isEmpty()) {
            System.out.print("Введите название (name): ");
            name = scanner.nextLine();
            if (name.isEmpty()) {
                System.out.println("Строка не может быть пустой!");
            }
        }
        ticket.setName(name);
    }

    public static void setCoordinates(Ticket ticket) {
        Coordinates coordinates = new Coordinates();

        setCoordinateX(coordinates);
        setCoordinateY(coordinates);

        ticket.setCoordinates(coordinates);
    }

    public static void setCoordinateX(Coordinates coordinates) {
        while (true) {
            try {
                System.out.print("Введите координату X (Coordinates x): ");
                String line = scanner.nextLine();
                if (line.isEmpty()) {
                    System.out.println("Координата X должна быть дробным числом");
                } else {
                    coordinates.setX(Float.valueOf(line));
                    break;
                }
            } catch (CoordinateXException e) {
                System.out.println("Координата X должна быть больше " + COORDINATE_X_MIN_LIMIT);
            } catch (NumberFormatException e) {
                System.out.println("Координата X должна быть дробным числом");
            }
        }
    }

    public static void setCoordinateY(Coordinates coordinates) {
        while (true) {
            try {
                System.out.print("Введите координату Y (Coordinates y): ");
                String line = scanner.nextLine();
                if (line.isEmpty()) {
                    System.out.println("Координата Y должна быть дробным числом");
                } else {
                    coordinates.setY(Float.valueOf(line));
                    break;
                }

            } catch (NumberFormatException e) {
                System.out.println("Координата Y должна быть дробным числом");
            }
        }
    }
}
