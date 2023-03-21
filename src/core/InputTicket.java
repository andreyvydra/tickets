package core;

import core.exceptions.CoordinateXException;
import core.exceptions.EmptyNameException;
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
    private static final OutputHandler outputHandler = new OutputHandler();
    
    public static Ticket getTicketFromConsole(CollectionManager collectionManager) {
        Ticket ticket = getTicketWithoutIdFromConsole();
        setId(ticket, collectionManager);
        return ticket;

    }

    public static Ticket getTicketWithoutIdFromConsole() {
        Ticket ticket = new Ticket();
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
        outputHandler.println("Создание человека");

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
                outputHandler.print("Введите координату x (Person x): ");
                location.setX(Double.parseDouble(scanner.nextLine()));
                break;
            } catch (NumberFormatException e) {
                outputHandler.println("Введен текст");
            }
        }
    }

    public static void setLocationY(Location location) {
        while (true) {
            try {
                outputHandler.print("Введите координату y (Person y): ");
                location.setY(Integer.parseInt(scanner.nextLine()));
                break;
            } catch (NumberFormatException e) {
                outputHandler.println("Введен текст");
            } catch (NullPointerException e) {
                outputHandler.println("Введен null");
            }
        }
    }

    public static void setLocationZ(Location location) {
        while (true) {
            try {
                outputHandler.print("Введите координату z (Person z): ");
                location.setZ(Float.parseFloat(scanner.nextLine()));
                break;
            } catch (NumberFormatException e) {
                outputHandler.println("Введен текст");
            }
        }
    }

    public static boolean isLocationInputted() {
        outputHandler.println("Хотите ли вы ввести значения локации? \n" +
                "Нажмите enter или введи что-нибудь [нет, да]");
        String line = scanner.nextLine();
        return !line.isEmpty();
    }

    public static void setPersonsNationality(Person person) {
        printCountries();
        while (true) {
            try {
                outputHandler.print("Национальность (nationality): ");
                person.setNationality(Country.valueOf(scanner.nextLine().toUpperCase()));
                break;
            } catch (IllegalArgumentException e) {
                outputHandler.println("Введена неправильная страна");
            }
        }
    }

    public static void printCountries() {
        outputHandler.println("Доступные страны");
        for (Country country : Country.values()) {
            outputHandler.println(country);
        }
    }

    public static void setPersonsHairColor(Person person) {
        printColors();
        while (true) {
            try {
                outputHandler.print("Цвет волос (hairColor): ");
                String stringColor = scanner.nextLine().toUpperCase();
                if (stringColor.isEmpty()) {
                    person.setHairColor(null);
                } else {
                    person.setHairColor(Color.valueOf(stringColor));
                }
                break;
            } catch (IllegalArgumentException e) {
                outputHandler.println("Введён неправильный цвет волос");
            }
        }
    }

    public static void setPersonsEyeColor(Person person) {
        printColors();
        while (true) {
            try {
                outputHandler.print("Цвет глаз (eyeColor): ");
                person.setEyeColor(Color.valueOf(scanner.nextLine().toUpperCase()));
                break;
            } catch (IllegalArgumentException e) {
                outputHandler.println("Введён неправильный цвет глаз");
            }
        }
    }

    public static void printColors() {
        outputHandler.println("Доступные цвета");
        for (Color color : Color.values()) {
            outputHandler.println(color);
        }
    }

    public static void setPersonsBirthday(Person person) {
        outputHandler.println("Пример ввода дня рождения: Пример ввода дня рождения: 2007-12-03T10:15:30. ISO FORMAT");
        while (true) {
            try {
                outputHandler.print("Введите день рождения (birthday): ");
                String stringBirthday = scanner.nextLine();
                if (stringBirthday.isEmpty()) {
                    person.setBirthday(null);
                } else {
                    person.setBirthday(LocalDateTime.parse(stringBirthday, DateTimeFormatter.ISO_LOCAL_DATE_TIME));
                }
                break;
            } catch (DateTimeParseException e) {
                outputHandler.println("Неправильно введена дата. Пример: 2007-12-03T10:15:30");
            }
        }
    }

    public static void setType(Ticket ticket) {
        printAllTypesOfTickets();

        while (true) {
            try {
                outputHandler.print("Введите вид бидета (type): ");
                ticket.setType(TicketType.valueOf(scanner.nextLine().toUpperCase()));
                break;
            } catch (IllegalArgumentException e) {
                outputHandler.println("Введён неправильный вид билета");
            }
        }
    }

    public static void printAllTypesOfTickets() {
        outputHandler.println("Виды билетов: ");
        for (TicketType value : TicketType.values()) {
            outputHandler.println(value);
        }
    }

    public static void setPrice(Ticket ticket) {
        while (true) {
            try {
                outputHandler.print("Введите цену (price): ");
                String line = scanner.nextLine();
                if (line.isEmpty()) {
                    outputHandler.println("Цена должна быть положительным числом");
                } else {
                    ticket.setPrice(Long.parseLong(line));
                    break;
                }
            } catch (ValueIsNotPositiveException | NumberFormatException e) {
                outputHandler.println("Цена должна быть положительным числом");
            }
        }
    }

    public static void setCreationDate(Ticket ticket) {
        ticket.setCreationDate(ZonedDateTime.now());
    }

    public static void setId(Ticket ticket, CollectionManager collectionManager) {
        long id = collectionManager.getNewId();
        try {
            ticket.setId(id);
        } catch (ValueIsNotPositiveException e) {
            System.out.println("id не может быть неположительным, возможно переполнение");
        }
    }

    public static void setName(Ticket ticket) {
        while (true) {
            try {
                outputHandler.print("Введите название (name): ");
                String name = scanner.nextLine();
                ticket.setName(name);
                break;
            } catch (EmptyNameException e) {
                outputHandler.println("Строка не может быть пустой!");
            }
        }
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
                outputHandler.print("Введите координату X (Coordinates x): ");
                String line = scanner.nextLine();
                if (line.isEmpty()) {
                    outputHandler.println("Координата X должна быть дробным числом");
                } else {
                    coordinates.setX(Float.valueOf(line));
                    break;
                }
            } catch (CoordinateXException e) {
                outputHandler.println("Координата X должна быть больше " + COORDINATE_X_MIN_LIMIT);
            } catch (NumberFormatException e) {
                outputHandler.println("Координата X должна быть дробным числом");
            }
        }
    }

    public static void setCoordinateY(Coordinates coordinates) {
        while (true) {
            try {
                outputHandler.print("Введите координату Y (Coordinates y): ");
                String line = scanner.nextLine();
                if (line.isEmpty()) {
                    outputHandler.println("Координата Y должна быть дробным числом");
                } else {
                    coordinates.setY(Float.valueOf(line));
                    break;
                }

            } catch (NumberFormatException e) {
                outputHandler.println("Координата Y должна быть дробным числом");
            }
        }
    }
}
