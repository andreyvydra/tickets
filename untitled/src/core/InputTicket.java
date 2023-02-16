package core;

import data.*;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.EnumSet;
import java.util.Scanner;

public class InputTicket {
    private CollectionManager collectionManager;

    public InputTicket(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }


    public Ticket getTicketFromConsole() {
        Scanner scanner = new Scanner(System.in);
        Ticket ticket = new Ticket();

        this.setId(ticket);
        this.setName(ticket, scanner);
        this.setCoordinate(ticket, scanner);
        this.setCreationDate(ticket);
        this.setPrice(ticket, scanner);
        this.setType(ticket, scanner);
        this.setPerson(ticket, scanner);
        return ticket;

    }
    public void setPerson(Ticket ticket, Scanner scanner) {
        Person person = new Person();
        System.out.println("Создание человека");

        LocalDateTime birthday;
        while (true) {
            try {
                System.out.print("Введите день рождения (birthday): ");
                String stringBirthday = scanner.nextLine();
                if (stringBirthday.equals("")) {
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
                if (stringColor.equals("")) {
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
                if (stringX.equals("")) {
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
                if (stringY.equals("")) {
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
                if (stringZ.equals("")) {
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

    public void setType(Ticket ticket, Scanner scanner) {
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
            } catch (Exception e) {
                System.out.println("Введён неправильный вид билета");
            }
        }
        ticket.setType(type);
    }

    public void setPrice(Ticket ticket, Scanner scanner) {
        long price = -1;
        while (price < 1) {
            try {
                System.out.print("Введите цену (price): ");
                String stringPrice = scanner.nextLine();
                price = Long.parseLong(stringPrice);
                ticket.setPrice(price);
                break;
            } catch (Exception e) {
                System.out.println("Цена должна быть числом и положительным");
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

    public void setName(Ticket ticket, Scanner scanner) {
        String name = "";
        while (name.equals("")) {
            System.out.print("Введите название (name): ");
            name = scanner.nextLine();
            if (name.equals("")) {
                System.out.println("Строка не может быть пустой!");
            }
        }
        ticket.setName(name);
    }

    public void setCoordinate(Ticket ticket, Scanner scanner) {
        Coordinates coordinates = new Coordinates();
        String stringX;
        while (true) {
            try {
                System.out.print("Введите координату X (Coordinates x): ");
                stringX = scanner.nextLine();
                if (stringX.equals("")) {
                    throw new Exception();
                }
                Float x = Float.valueOf(stringX);
                coordinates.setX(x);
                break;
            } catch (Exception e) {
                System.out.println("Координата X должна быть числом большим -873 и не равна null");
            }
        }

        String stringY;
        while (true) {
            try {
                System.out.print("Введите координату Y (Coordinates y): ");
                stringY = scanner.nextLine();
                if (stringY.equals("")) {
                    throw new Exception();
                }
                Float y = Float.valueOf(stringY);
                coordinates.setY(y);
                break;
            } catch (Exception e) {
                System.out.println("Координата Y должна быть числом и не равна null");
            }
        }

        ticket.setCoordinates(coordinates);
    }
}
