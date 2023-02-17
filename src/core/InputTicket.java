package core;

import application.App;
import core.exceptions.CoordinateXException;
import core.exceptions.ValueIsNotPositiveException;
import data.*;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class InputTicket {
    private CollectionManager collectionManager;
    private Scanner scanner;

    public InputTicket(App app) {
        this.collectionManager = app.getCollectionManager();
        this.scanner = app.getScanner();
    }


    public Ticket getTicketFromConsole() {
        Ticket ticket = new Ticket();

        this.setId(ticket);
        this.setName(ticket);
        this.setCoordinates(ticket);
        this.setCreationDate(ticket);
        this.setPrice(ticket);
        this.setType(ticket);
        this.setPerson(ticket);
        return ticket;

    }

    public void setPerson(Ticket ticket) {
        Person person = new Person();
        System.out.println("Создание человека");

        this.setPersonsBirthday(person);
        this.setPersonsEyeColor(person);
        this.setPersonsHairColor(person);
        this.setPersonsNationality(person);
        if (!this.willLocationInputted()) {
            person.setLocation(null);
        } else {
            this.setLocation(person);
        }

        ticket.setPerson(person);
    }

    public void setLocation(Person person) {
        Location location = new Location();
        this.setLocationX(location);
        this.setLocationY(location);
        this.setLocationZ(location);

        person.setLocation(location);
    }

    public void setLocationX(Location location) {
        while (true) {
            try {
                System.out.print("Введите координату x (Person x): ");
                location.setX(Double.parseDouble(scanner.nextLine()));
                break;
            } catch (NumberFormatException e) {
                System.out.println("Введена пустая строка либо текст");
            }
        }
    }

    public void setLocationY(Location location) {
        while (true) {
            try {
                System.out.print("Введите координату y (Person y): ");
                location.setY(Integer.parseInt(scanner.nextLine()));
                break;
            } catch (NumberFormatException e) {
                System.out.println("Введена пустая строка либо текст");
            }
        }
    }

    public void setLocationZ(Location location) {
        while (true) {
            try {
                System.out.print("Введите координату z (Person z): ");
                location.setZ(Float.parseFloat(scanner.nextLine()));
                break;
            } catch (NumberFormatException e) {
                System.out.println("Введена пустая строка либо текст");
            }
        }
    }

    public boolean willLocationInputted() {
        System.out.println("Хотите ли вы ввести значения локации? \n" +
                "Press enter or input something [no, yes]");
        String line = scanner.nextLine();
        return !line.isEmpty();
    }

    public void setPersonsNationality(Person person) {
        this.printCountries();
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

    public void printCountries() {
        System.out.println("Доступные страны");
        for (Country country : Country.values()) {
            System.out.println(country);
        }
    }

    public void setPersonsHairColor(Person person) {
        this.printColors();
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

    public void setPersonsEyeColor(Person person) {
        this.printColors();
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

    public void printColors() {
        System.out.println("Доступные цвета");
        for (Color color : Color.values()) {
            System.out.println(color);
        }
    }

    public void setPersonsBirthday(Person person) {
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

    public void setType(Ticket ticket) {
        this.printAllTypesOfTickets();

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

    public void printAllTypesOfTickets() {
        System.out.println("Виды билетов: ");
        for (TicketType value : TicketType.values()) {
            System.out.println(value);
        }
    }

    public void setPrice(Ticket ticket) {
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

    public void setCreationDate(Ticket ticket) {
        ticket.setCreationDate(ZonedDateTime.now());
    }

    public void setId(Ticket ticket) {
        long id = this.collectionManager.getNewId();
        ticket.setId(id);
    }

    public void setName(Ticket ticket) {
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

    public void setCoordinates(Ticket ticket) {
        Coordinates coordinates = new Coordinates();

        this.setCoordinateX(coordinates);
        this.setCoordinateY(coordinates);

        ticket.setCoordinates(coordinates);
    }

    public void setCoordinateX(Coordinates coordinates) {
        while (true) {
            try {
                System.out.print("Введите координату X (Coordinates x): ");
                String line = scanner.nextLine();
                if (line.isEmpty()) {
                    System.out.println("Координата X должна быть дробным числом и не может быть равна null");
                } else {
                    coordinates.setX(Float.valueOf(line));
                    break;
                }
            } catch (CoordinateXException e) {
                System.out.println("Координата X должна быть больше -873");
            } catch (NumberFormatException e) {
                System.out.println("Координата X должна быть дробным числом и не может быть равна null");
            }
        }
    }

    public void setCoordinateY(Coordinates coordinates) {
        while (true) {
            try {
                System.out.print("Введите координату Y (Coordinates y): ");
                String line = scanner.nextLine();
                if (line.isEmpty()) {
                    System.out.println("Координата Y должна быть дробным числом и не может быть равна null");
                } else {
                    coordinates.setY(Float.valueOf(line));
                    break;
                }

            } catch (NumberFormatException e) {
                System.out.println("Координата Y должна быть дробным числом и не может быть равна null");
            }
        }
    }
}
