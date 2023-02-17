package console.commads;

import console.commads.generalCommands.Command;

/**
 * Help command prints info about commands
 *
 * @see Command
 * @see console.Console
 */
public class HelpCommand implements Command {

    @Override
    public void execute(String command) {
        System.out.println("help : вывести справку по доступным командам\n" +
                "info : вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)\n" +
                "show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении\n" +
                "add {element} : добавить новый элемент в коллекцию\n" +
                "update id {element} : обновить значение элемента коллекции, id которого равен заданному\n" +
                "remove_by_id id : удалить элемент из коллекции по его id\n" +
                "clear : очистить коллекцию\n" +
                "save : сохранить коллекцию в файл\n" +
                "execute_script file_name : считать и исполнить скрипт из указанного файла." +
                "В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.\n" +
                "exit : завершить программу (без сохранения в файл)\n" +
                "add_if_max {element} : добавить новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции\n" +
                "remove_lower {element} : удалить из коллекции все элементы, меньшие, чем заданный\n" +
                "history : вывести последние 8 команд (без их аргументов)\n" +
                "group_counting_by_creation_date : сгруппировать элементы коллекции по значению поля creationDate, вывести количество элементов в каждой группе\n" +
                "count_greater_than_type type : вывести количество элементов, значение поля type которых больше заданного\n" +
                "print_field_ascending_type : вывести значения поля type всех элементов в порядке возрастания");
    }
}
