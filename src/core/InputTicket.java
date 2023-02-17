package core;

import application.App;
import core.exceptions.CoordinateXException;
import data.*;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
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
        this.setCoordinate(ticket);
        this.setCreationDate(ticket);
        this.setPrice(ticket);
        this.setType(ticket);
        this.setPerson(ticket);
        return ticket;

    }

    public void setPerson(Ticket ticket) {
        Person person = new Person();
        System.out.println("Создание человека");

        LocalDateTime birthday;
        while (true) {
            try {
                System.out.print("Введите день рождения (birthday): ");
                String stringBirthday = scanner.nextLine();
                if (stringBirthday.isEmpty()) {
                    birthday = null;
                } else {
                    birthday = LocalDateTime.parse(stringBirthday, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
                }
                break;
            } catch (Exception e) {
                System.out.println("День рождения должен вводиться в формате ISO 8601\n (Пример: 2023-02-16T08:39:26)");
            }
        }

        person.setBirthday(birthday);

        System.out.println("Доступные цвета");
        for (Color color : Color.values()) {
            System.out.println(color);
        }

        Color eyeColor;
        while (true) {
            try {
                System.out.print("Цвет глаз (eyeColor): ");
                eyeColor = Color.valueOf(scanner.nextLine());
                break;
            } catch (Exception e) {
                System.out.println("Введён неправильный цвет глаз");
            }
        }
        person.setEyeColor(eyeColor);

        Color hairColor;
        while (true) {
            try {
                System.out.print("Цвет волос (eyeColor): ");
                String stringColor = scanner.nextLine();
                if (stringColor.isEmpty()) {
                    hairColor = null;
                } else {
                    hairColor = Color.valueOf(stringColor);
                }
                break;
            } catch (Exception e) {
                System.out.println("Введён неправильный цвет волос");
            }
        }
        person.setHairColor(hairColor);

        System.out.println("Доступные страны");
        for (Country country : Country.values()) {
            System.out.println(country);
        }
        Country nationality;
        while (true) {
            try {
                System.out.print("Цвет волос (eyeColor): ");
                nationality = Country.valueOf(scanner.nextLine());
                break;
            } catch (Exception e) {
                System.out.println("Введён неправильный цвет волос");
            }
        }
        person.setNationality(nationality);

        Location location = new Location();
        double x;
        while (true) {
            try {
                System.out.print("Введите координату x (Person x): ");
                String stringX = scanner.nextLine();
                if (stringX.isEmpty()) {
                    throw new Exception();
                }
                x = Double.parseDouble(stringX);
                location.setX(x);
                break;
            } catch (Exception e) {
                System.out.println("Координата X должна быть числом и не null");
            }
        }

        int y;
        while (true) {
            try {
                System.out.print("Введите координату Y (Person Y): ");
                String stringY = scanner.nextLine();
                if (stringY.isEmpty()) {
                    throw new Exception();
                }
                y = Integer.parseInt(stringY);
                location.setY(y);
                break;
            } catch (Exception e) {
                System.out.println("Координата Y должна быть числом и не null");
            }
        }

        float z;
        while (true) {
            try {
                System.out.print("Введите координату Z (Person Z): ");
                String stringZ = scanner.nextLine();
                if (stringZ.isEmpty()) {
                    throw new Exception();
                }
                z = Integer.parseInt(stringZ);
                location.setZ(z);
                break;
            } catch (Exception e) {
                System.out.println("Координата Z должна быть числом и не null");
            }
        }
        person.setLocation(location);
        ticket.setPerson(person);

    }

    public void setType(Ticket ticket) {
        System.out.println("Виды билетов: ");
        for (TicketType value : TicketType.values()) {
            System.out.println(value);
        }

        TicketType type;
        while (true) {
            try {
                System.out.print("Введите вид бидета (type): ");
                type = TicketType.valueOf(scanner.nextLine());
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Введён неправильный вид билета");
            }
        }
        ticket.setType(type);
    }

    public void setPrice(Ticket ticket) {
        long price;
        while (true) {
            System.out.print("Введите цену (price): ");
            if (!scanner.hasNextLine() || !scanner.hasNextLong()) {
                System.out.println("Цена должна быть положительным числом");
                scanner.nextLine();
            }
            price = Long.parseLong(scanner.nextLine());
            if (price <= 0) {
                System.out.println("Цена должна быть положительным числом");
            } else {
                ticket.setPrice(price);
                break;
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

    public void setCoordinate(Ticket ticket) {
        Coordinates coordinates = new Coordinates();

        while (true) {
            try {
                System.out.print("Введите координату X (Coordinates x): ");
                if (!scanner.hasNextFloat()) {
                    scanner.nextLine();
                    System.out.println("Координата X должна быть дробным числом большим -873 и не равна null");
                } else {
                    if (scanner.hasNextLine()) {
                        coordinates.setX(Float.valueOf(scanner.nextLine()));
                        break;
                    } else {
                        System.out.println(1);
                    }
                }
            } catch (CoordinateXException e) {
                System.out.println(e);
            }
        }

        while (true) {
            System.out.print("Введите координату Y (Coordinates y): ");
            if (!scanner.hasNextFloat() || !scanner.hasNextLine()) {
                scanner.nextLine();
                System.out.println("Координата Y должна быть дробным числом и не равна null");
            } else {
                coordinates.setY(Float.valueOf(scanner.nextLine()));
                break;
            }
        }

        ticket.setCoordinates(coordinates);
    }

}
