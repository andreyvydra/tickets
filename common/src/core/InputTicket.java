package core;

import core.exceptions.*;
import data.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Scanner;

import static core.Globals.COORDINATE_X_MIN_LIMIT;
import static core.Globals.Network.USERNAME;

public class InputTicket {
    private static final Scanner scanner = new Scanner(System.in);
    private static final OutputHandler outputHandler = new OutputHandler();

    public static Ticket getTicketWithoutIdFromConsole(HashMap<String, String> user) {
        Ticket ticket = new Ticket();
        try {
            ticket.setCreatorLogin(user.get(USERNAME));
            setName(ticket);
            setCoordinates(ticket);
            setCreationDate(ticket);
            setPrice(ticket);
            setEnumValue(ticket, TicketType.class, ticket.getClass().getMethod("setType", TicketType.class), true);
            setPerson(ticket);
        } catch (NoSuchMethodException e) {
            outputHandler.println("Отсутствует указанный метод");
        }
        return ticket;
    }

    public static void setPerson(Ticket ticket) throws NoSuchMethodException {
        Person person = new Person();
        outputHandler.println("Создание человека");

        setPersonsBirthday(person);
        setEnumValue(person, Color.class, person.getClass().getMethod("setEyeColor", Color.class), true);
        setEnumValue(person, Color.class, person.getClass().getMethod("setHairColor", Color.class), false);
        setEnumValue(person, Country.class, person.getClass().getMethod("setNationality", Country.class), true);
        if (!isLocationInputted()) {
            person.setLocation(null);
        } else {
            setLocation(person);
        }
        try {
            ticket.setPerson(person);
        } catch (EmptyFieldException e) {
            outputHandler.println(e);
        }
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
                location.setX(Double.parseDouble(scanner.nextLine().replace(",", ".")));
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
                outputHandler.println("Введен текст или дробное число");
            } catch (EmptyFieldException e) {
                outputHandler.println(e);
            }
        }
    }

    public static void setLocationZ(Location location) {
        while (true) {
            try {
                outputHandler.print("Введите координату z (Person z): ");
                location.setZ(Float.parseFloat(scanner.nextLine().replace(",", ".")));
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

    public static void setEnumValue(Object object, Class<? extends Enum> eEnum, Method setValue, boolean isNotNull) {
        printEnumValues(eEnum);
        Object[] values = eEnum.getEnumConstants();

        while (true) {
            try {
                outputHandler.print("Введите вид " + eEnum.getName() + ": ");
                String value = scanner.nextLine().trim().toUpperCase();
                if (value.isEmpty() && isNotNull) {
                    outputHandler.println("Ввели пустую строчку!");
                    continue;
                } else if (value.isEmpty()) {
                    setValue.invoke(object, (Object) null);
                    break;
                }
                try {
                    int number = Integer.parseInt(value) - 1;
                    if (0 > number || number >= values.length) {
                        throw new EnumValuesOutOfRangeException();
                    }
                    setValue.invoke(object, values[number]);
                } catch (NumberFormatException e) {
                    boolean flag = false;
                    for (Object item : eEnum.getEnumConstants()) {
                        if (item.toString().startsWith(value)) {
                            setValue.invoke(object, item);
                            flag = true;
                        }
                    }
                    if (!flag) {
                        throw new IncorrectEnumNameException();
                    }
                } catch (EnumValuesOutOfRangeException e) {
                    outputHandler.println(e);
                    continue;
                }
                break;
            } catch (IllegalArgumentException | InvocationTargetException e) {
                outputHandler.println("Введён неправильный вид " + eEnum.getName());
            } catch (IllegalAccessException e) {
                outputHandler.println("Отсутствует доступ к методу");
            } catch (IncorrectEnumNameException e) {
                outputHandler.println(e);
            }
        }
    }


    public static void printEnumValues(Class<? extends Enum> eEnum) {
        outputHandler.println("Виды " + eEnum.getName() + ": ");
        Object[] values = eEnum.getEnumConstants();
        for (int i = 0; i < values.length; i++) {
            outputHandler.println((i + 1) + ". " + values[i]);
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
        try {
            ticket.setCreationDate(ZonedDateTime.now());
        } catch (EmptyFieldException e) {
            outputHandler.println(e);
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

        try {
            ticket.setCoordinates(coordinates);
        } catch (EmptyFieldException e) {
            outputHandler.println(e);
        }
    }

    public static void setCoordinateX(Coordinates coordinates) {
        while (true) {
            try {
                outputHandler.print("Введите координату X (Coordinates x): ");
                String line = scanner.nextLine();
                if (line.replace(",", ".").isEmpty()) {
                    outputHandler.println("Координата X должна быть дробным числом");
                } else {
                    coordinates.setX(Float.valueOf(line.replace(",", ".")));
                    break;
                }
            } catch (CoordinateXException e) {
                outputHandler.println("Координата X должна быть больше " + COORDINATE_X_MIN_LIMIT);
            } catch (NumberFormatException e) {
                outputHandler.println("Координата X должна быть дробным числом");
            } catch (EmptyFieldException e) {
                outputHandler.println(e);
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
                    coordinates.setY(Float.valueOf(line.replace(",", ".")));
                    break;
                }

            } catch (NumberFormatException e) {
                outputHandler.println("Координата Y должна быть дробным числом");
            } catch (EmptyFieldException e) {
                outputHandler.println(e);
            }
        }
    }
}
