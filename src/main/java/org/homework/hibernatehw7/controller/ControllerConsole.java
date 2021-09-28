package org.homework.hibernatehw7.controller;

import org.homework.hibernatehw7.config.ScannerConsole;
import org.homework.hibernatehw7.controller.interfaces.Controller;
import org.homework.hibernatehw7.model.*;

import java.util.Scanner;

public class ControllerConsole<T extends BaseModel<ID>, ID> implements Controller {

    private final GetDeleteCommands<Developer, Long> developerMethods = new GetDeleteCommands<>(Developer.class);
    private final GetDeleteCommands<Project, Long> projectMethods = new GetDeleteCommands<>(Project.class);
    private final GetDeleteCommands<Company, Long> companyMethods = new GetDeleteCommands<>(Company.class);
    private final GetDeleteCommands<Skill, Long> skillMethods = new GetDeleteCommands<>(Skill.class);
    private final GetDeleteCommands<Customer, Long> customerMethods = new GetDeleteCommands<>(Customer.class);
    private final Scanner scanner = ScannerConsole.getInstance();
    private final String RED = "\u001b[31m";
    private final String YELLOW = "\u001b[33m";
    private final String COLOUR3 = "\u001b[40m";
    private String model = "";
    private String crud = "";
    private String special = "";
    private String result = "";
    private final String startList = YELLOW + COLOUR3 +
                                     "_________________________________________________________________________________________________________________________\n" +
                                     "| DEVELOPER    -> DV  | PROJECT     -> PR   | SKILL          -> SK  | CUSTOMER        -> CS    | COMPANY         -> CM  |\n" +
                                     "| GET          -> G   | CREATE      -> C    | UPDATE         -> U   | DELETE          -> D     |                        |\n" +
                                     "| PRO withDate -> PWD | DEV byLevel -> DBL  | DEV byActivity -> DBA | DEV byProjectId -> DBD   | DEV SumSalaries -> SS  |\n" +
                                     "| SPECIAL      -> SPR | GET ByID    -> ByID | GET ALL        -> A   | THIS LIST       -> Q     | STOP            -> S   |\n" +
                                     "-------------------------------------------------------------------------------------------------------------------------\n";


    @Override
    public void start() {
        System.out.println(startList);
        while (scanner.hasNext()) {
            result = getRead();
            ifHasModel();
        }
    }

    private String getRead() {
        String read = scanner.next();
        model = "";
        crud = "";
        special = "";
        if (read.equalsIgnoreCase("get") | read.equalsIgnoreCase("g")) {
            crud = "get";
            model = getModel();
            read = crud + model;
            return read;
        }
        if (read.equalsIgnoreCase("create") | read.equalsIgnoreCase("c") | read.equalsIgnoreCase("cre")) {
            crud = "create";
            model = getModel();
            read = crud + model;
            return read;
        }
        if (read.equalsIgnoreCase("update") | read.equalsIgnoreCase("u") | read.equalsIgnoreCase("up")) {
            crud = "update";
            model = getModel();
            read = crud + model;
            return read;
        }
        if (read.equalsIgnoreCase("delete") | read.equalsIgnoreCase("d") | read.equalsIgnoreCase("del")) {
            crud = "delete";
            model = getModel();
            read = crud + model;
            return read;
        }
        if (read.equalsIgnoreCase("developer") | read.equalsIgnoreCase("dev") | read.equalsIgnoreCase("dv")) {
            model = "developer";
            crud = getCrud();
            read = crud + model;
            return read;
        }
        if (read.equalsIgnoreCase("customer") | read.equalsIgnoreCase("cus") | read.equalsIgnoreCase("cs")) {
            model = "customer";
            crud = getCrud();
            read = crud + model;
            return read;
        }
        if (read.equalsIgnoreCase("company") | read.equalsIgnoreCase("com") | read.equalsIgnoreCase("cm")) {
            model = "company";
            crud = getCrud();
            read = crud + model;
            return read;
        }
        if (read.equalsIgnoreCase("skill") | read.equalsIgnoreCase("skl") | read.equalsIgnoreCase("sk")) {
            model = "skill";
            crud = getCrud();
            read = crud + model;
            return read;
        }
        if (read.equalsIgnoreCase("project") | read.equalsIgnoreCase("pro") | read.equalsIgnoreCase("pr")) {
            model = "project";
            crud = getCrud();
            read = crud + model;
            return read;
        }
        if (read.equalsIgnoreCase("SPECIAL") | read.equalsIgnoreCase("specR") | read.equalsIgnoreCase("spr")) {
            getSpecial();
            read = special;
            return read;
        }
        if (read.equalsIgnoreCase("byProjectId") | read.equalsIgnoreCase("DBD")) {
            special = "byProjectId";
            getSpecial();
            read = special;
            return read;
        }
        if (read.equalsIgnoreCase("byActivity") | read.equalsIgnoreCase("DBA")) {
            special = "byActivity";
            getSpecial();
            read = special;
            return read;
        }
        if (read.equalsIgnoreCase("byLevel") | read.equalsIgnoreCase("DBL")) {
            special = "byLevel";
            getSpecial();
            read = special;
            return read;
        }
        if (read.equalsIgnoreCase("withDate") | read.equalsIgnoreCase("PWD")) {
            special = "withDate";
            getSpecial();
            read = special;
            return read;
        }
        if (read.equalsIgnoreCase("SumSalaries") | read.equalsIgnoreCase("SS")) {
            special = "SumSalaries";
            getSpecial();
            read = special;
            return read;
        }
        if (read.equalsIgnoreCase("list") | read.equalsIgnoreCase("main") | read.equalsIgnoreCase("q")) {
            System.out.println(startList);
            return read;
        }
        if (read.equalsIgnoreCase("all") | read.equalsIgnoreCase("a")) {
            crud = "all";
            model = getModel();
            read = crud + model;
            return read;
        }
        if (read.equalsIgnoreCase("id") | read.equalsIgnoreCase("byId")) {
            crud = "id";
            model = getModel();
            read = crud + model;
            return read;
        }
        if (read.equalsIgnoreCase("stop") | read.equalsIgnoreCase("s") | read.equalsIgnoreCase("sp")) {
            close();
        } else {
            System.out.println(RED + "Введите запрос корректно");
            start();
            return read;
        }
        return getRead();
    }

    private String getSpecial() {
        if (model.isEmpty() & crud.isEmpty()) {
            String specials = YELLOW + COLOUR3 +
                              "--------------------------------------------------------------------------------------------------\n" +
                              "| PROJECTS   withDATE    -> PWD   | DEVELOPERS ByLEVEL     -> DBL | DEVELOPERS ByACTIVITY -> DBA |\n" +
                              "| DEVELOPERS ByProjectID -> DBD   | DEVELOPERS SumSALARIES -> SS  | MAIN LIST             -> Q   |\n" +
                              "---------------------------------------------------------------------------------------------------\n";
            if (special.equalsIgnoreCase("withDate") | special.equalsIgnoreCase("PWD")) {
                special = "withDate";
                System.out.print("\n  ✅RESULT \uD83D\uDC47");
                projectMethods.getListProjectsWithDate();
                System.out.println(specials);
                return special;
            }
            if (special.equalsIgnoreCase("byLevel") | special.equalsIgnoreCase("DBL")) {
                special = "byLevel";
                System.out.print("\n  ✅RESULT \uD83D\uDC47\n");
                developerMethods.getByLevel();
                System.out.println(specials);
                return special;
            }
            if (special.equalsIgnoreCase("byActivity") | special.equalsIgnoreCase("DBA")) {
                special = "byActivity";
                System.out.print("\n  ✅RESULT \uD83D\uDC47\n");
                developerMethods.getByActivity();
                System.out.println(specials);
                return special;
            }
            if (special.equalsIgnoreCase("byProjectId") | special.equalsIgnoreCase("DBD")) {
                special = "byProjectId";
                System.out.print("\n  ✅RESULT \uD83D\uDC47");
                developerMethods.getByProjectID();
                System.out.println(specials);
                return special;
            }
            if (special.equalsIgnoreCase("SumSalaries") | special.equalsIgnoreCase("SS")) {
                special = "SumSalaries";
                System.out.print("\n  ✅RESULT \uD83D\uDC47");
                developerMethods.getSumSalaries();
                System.out.println(specials);
                return special;
            }
            if (special.equalsIgnoreCase("list") | special.equalsIgnoreCase("q")) {
                special = startList;
                start();
                System.out.println(startList);
                return special;
            }
            if (special.equalsIgnoreCase("stop") | special.equalsIgnoreCase("s") | special.equalsIgnoreCase("sp")) {
                close();
            } else {
                System.out.println(RED + " Choose query \uD83D\uDC47" + specials);
                special = scanner.next();
                getSpecial();
            }
            return special;
        }
        return special;
    }

    private void ifHasModel() {
        if (result.contains("company")) {
            if (result.contains("all")) {
                companyMethods.getAll();
                model = "";
                start();
            }
            if (result.contains("id") | result.equalsIgnoreCase("byId")) {
                companyMethods.getById();
                model = "";
                start();
            }
            if (result.contains("get")) {
                System.out.println(getBySmall());
                model = "";
                start();
            }
            if (result.contains("update")) {
                CreateUpdateCommands.getInstance().updateCompany();
                start();
            }
            if (result.contains("create")) {
                CreateUpdateCommands.getInstance().createCompany();
                start();
            }
            if (result.contains("delete")) {
                companyMethods.delete();
                start();
            }
            if (result.equalsIgnoreCase("main") | result.equalsIgnoreCase("list") | result.equalsIgnoreCase("q")) {
                start();
            }
            if (result.equalsIgnoreCase("stop") | result.equalsIgnoreCase("s") | result.equalsIgnoreCase("sp")) {
                close();
            }
            System.out.println(startList);
        }
        if (result.contains("customer")) {
            if (result.contains("id") | result.contains("byId")) {
                customerMethods.getById();
                model = "";
                start();
            }
            if (result.contains("all")) {
                customerMethods.getAll();
                model = "";
                start();
            }
            if (result.contains("get")) {
                System.out.println(getBySmall());
            }
            if (result.contains("update")) {
                CreateUpdateCommands.getInstance().updateCustomer();
                start();
            }
            if (result.contains("create")) {
                CreateUpdateCommands.getInstance().createCustomer();
                start();
            }
            if (result.contains("delete")) {
                customerMethods.delete();
                start();
            }
            if (result.equalsIgnoreCase("main") | result.equalsIgnoreCase("list") | result.equalsIgnoreCase("q")) {
                start();
            }
            if (result.equalsIgnoreCase("stop") | result.equalsIgnoreCase("s") | result.equalsIgnoreCase("sp")) {
                close();
            }
            System.out.println(startList);
        }
        if (result.contains("developer")) {
            if (result.contains("id") | result.contains("byId")) {
                developerMethods.getById();
                model = "";
                start();
            }
            if (result.contains("all")) {
                developerMethods.getAll();
                model = "";
                start();
            }
            if (result.contains("get")) {
                System.out.println(getByDeveloper());
            }
            if (result.contains("update")) {
                CreateUpdateCommands.getInstance().updateDeveloper();
                start();
            }
            if (result.contains("create")) {
                CreateUpdateCommands.getInstance().createDeveloper();
                start();
            }
            if (result.contains("delete")) {
                developerMethods.delete();
                start();
            }
            if (result.equalsIgnoreCase("main") | result.equalsIgnoreCase("list") | result.equalsIgnoreCase("q")) {
                start();
            }
            if (result.equalsIgnoreCase("stop") | result.equalsIgnoreCase("s") | result.equalsIgnoreCase("sp")) {
                close();
            }
            System.out.println(startList);
        }
        if (result.contains("skill")) {
            if (result.contains("id") | result.contains("byId")) {
                skillMethods.getById();
                model = "";
                start();
            }
            if (result.contains("all")) {
                skillMethods.getAll();
                model = "";
                start();
            }
            if (result.contains("get")) {
                System.out.println(getBySmall());
            }
            if (result.contains("update")) {
                CreateUpdateCommands.getInstance().updateSkill();
                start();
            }
            if (result.contains("create")) {
                CreateUpdateCommands.getInstance().createSkill();
                start();
            }
            if (result.contains("delete")) {
                skillMethods.delete();
                start();
            }
            if (result.equalsIgnoreCase("main") | result.equalsIgnoreCase("list") | result.equalsIgnoreCase("q")) {
                start();
            }
            if (result.equalsIgnoreCase("stop") | result.equalsIgnoreCase("s") | result.equalsIgnoreCase("sp")) {
                close();
            }
            System.out.println(startList);
        }
        if (result.contains("project")) {
            if (result.contains("id") | result.contains("byId")) {
                projectMethods.getById();
                model = "";
                start();
            }
            if (result.contains("all")) {
                projectMethods.getAll();
                model = "";
                start();
            }
            if (result.contains("get")) {
                getByProject();
            }
            if (result.contains("update")) {
                CreateUpdateCommands.getInstance().updateProject();
                start();
            }
            if (result.contains("create")) {
                CreateUpdateCommands.getInstance().createProject();
                start();
            }
            if (result.contains("delete")) {
                projectMethods.delete();
                start();
            }
            if (result.equalsIgnoreCase("main") | result.equalsIgnoreCase("list") | result.equalsIgnoreCase("q")) {
                start();
            }
            if (result.equalsIgnoreCase("stop") | result.equalsIgnoreCase("s") | result.equalsIgnoreCase("sp")) {
                close();
            }
        }
    }

    private String getCrud() {
        if (crud.isEmpty()) {
            System.out.println(" Choose query ");
            String crudString = YELLOW + COLOUR3 +
                                "______________________________________________\n" +
                                "| GET    -> G | CREATE -> C | MAIN LIST -> Q |\n" +
                                "| UPDATE -> U | DELETE -> D | STOP      -> S |\n" +
                                "----------------------------------------------\n";
            System.out.println(crudString);
            String crud = scanner.next();
            if (crud.equalsIgnoreCase("get") | crud.equalsIgnoreCase("g")) {
                crud = "get";
                return crud;
            }
            if (crud.equalsIgnoreCase("create") | crud.equalsIgnoreCase("c")) {
                crud = "create";
                return crud;
            }
            if (crud.equalsIgnoreCase("update") | crud.equalsIgnoreCase("u")) {
                crud = "update";
                return crud;
            }
            if (crud.equalsIgnoreCase("delete") | crud.equalsIgnoreCase("d")) {
                crud = "delete";
                return crud;
            }
            if (crud.equalsIgnoreCase("main") | crud.equalsIgnoreCase("list") | crud.equalsIgnoreCase("q")) {
                crud = "q";
                start();
                return crud;
            }
            if (crud.equalsIgnoreCase("stop") | crud.equalsIgnoreCase("s") | crud.equalsIgnoreCase("sp")) {
                crud = "s";
                close();
                return crud;
            } else {
                System.out.println(RED + "Please, try again ");
                return getCrud();
            }
        }
        return crud;
    }

    private String getModel() {
        if (model.isEmpty()) {
            System.out.println("Выбери модель");
            String getModelList = YELLOW + COLOUR3 +
                                  "____________________________________________________\n" +
                                  "| COMPANY -> CM | CUSTOMER  -> CS | SKILL   -> SK |\n" +
                                  "| PROJECT -> PR | DEVELOPER -> DV |               |\n" +
                                  "| SPECIAL -> SP | MAIN LIST -> Q  | STOP     -> S |\n" +
                                  "----------------------------------------------------\n";
            System.out.println(getModelList);
            String model = scanner.next();
            if (model.equalsIgnoreCase("skill") | model.equalsIgnoreCase("skl") | model.equalsIgnoreCase("sk")) {
                model = "skill";
                return model;
            }
            if (model.equalsIgnoreCase("developer") | model.equalsIgnoreCase("dev") | model.equalsIgnoreCase("dv")) {
                model = "developer";
                return model;
            }
            if (model.equalsIgnoreCase("project") | model.equalsIgnoreCase("pro") | model.equalsIgnoreCase("pr")) {
                model = "project";
                return model;
            }
            if (model.equalsIgnoreCase("company") | model.equalsIgnoreCase("com") | model.equalsIgnoreCase("cm")) {
                model = "company";
                return model;
            }
            if (model.equalsIgnoreCase("customer") | model.equalsIgnoreCase("cus") | model.equalsIgnoreCase("cs")) {
                model = "customer";
                return model;
            }
            if (model.equalsIgnoreCase("special") | model.equalsIgnoreCase("sp")) {
                crud = "";
                return getSpecial();
            }
            if (model.equalsIgnoreCase("main") | model.equalsIgnoreCase("list") | model.equalsIgnoreCase("q")) {
                start();
            }
            if (model.equalsIgnoreCase("stop") | model.equalsIgnoreCase("s") | model.equalsIgnoreCase("sp")) {
                close();
            } else {
                System.out.println(RED + " Please, try again ");
                return getModel();
            }
        }
        return model;
    }

    private String getBySmall() {
        String getBySmall = YELLOW + COLOUR3 +
                            "_______________________________\n" +
                            "| BY ID     -> ID | ALL  -> A |\n" +
                            "| MAIN LIST -> Q  | STOP -> S |\n" +
                            "-------------------------------";
        System.out.println(getBySmall);
        String next = scanner.next();
        if (next.equalsIgnoreCase("byId") | next.equalsIgnoreCase("id")) {
            if (model.equalsIgnoreCase("company")) {
                companyMethods.getById();
                return getBySmall();
            }
            if (model.equalsIgnoreCase("customer")) {
                customerMethods.getById();
                return getBySmall();
            }
            if (model.equalsIgnoreCase("skill")) {
                skillMethods.getById();
                return getBySmall();
            }

        }
        if (next.equalsIgnoreCase("all") | next.equalsIgnoreCase("a")) {
            if (model.equalsIgnoreCase("company")) {
                companyMethods.getAll();
                return getBySmall();
            }
            if (model.equalsIgnoreCase("customer")) {
                customerMethods.getAll();
                return getBySmall();
            }
            if (model.equalsIgnoreCase("skill")) {
                skillMethods.getAll();
                return getBySmall();
            }
        }
        if (next.equalsIgnoreCase("main") | next.equalsIgnoreCase("list") | next.equalsIgnoreCase("q")) {
            next = "q";
            start();
            return next;
        }
        if (next.equalsIgnoreCase("stop") | next.equalsIgnoreCase("s") | next.equalsIgnoreCase("sp")) {
            next = "s";
            close();
            return next;
        } else {
            getBySmall();
        }
        return next;
    }

    private void getByProject() {
        String getBy = YELLOW + COLOUR3 +
                       "_______________________________________________\n" +
                       "| BY ID       -> ID    | All         -> A     |\n" +
                       "| WithDATE    -> PWD   | MAIN LIST   -> Q     |\n" +
                       "|                      | STOP        -> S     |\n" +
                       "-----------------------------------------------\n";
        System.out.println(getBy);
        String next = scanner.next();
        if (next.equalsIgnoreCase("byId") | next.equalsIgnoreCase("id")) {
            projectMethods.getById();
        }
        if (next.equalsIgnoreCase("all") | next.equalsIgnoreCase("a")) {
            projectMethods.getAll();
        }
        if (next.equalsIgnoreCase("withDate") | next.equalsIgnoreCase("PWD")) {
            projectsWithDate();
            return;
        }
        if (next.equalsIgnoreCase("main") | next.equalsIgnoreCase("list") | next.equalsIgnoreCase("q")) {
            start();
            return;
        }
        if (next.equalsIgnoreCase("stop") | next.equalsIgnoreCase("s") | next.equalsIgnoreCase("sp")) {
            close();
        } else {
            getByProject();
        }
    }

    private String getByDeveloper() {
        String getByDev = YELLOW + COLOUR3 +
                          "_______________________________________________\n" +
                          "| BY ID       -> ID    | All         -> A     |\n" +
                          "| SumSALARIES -> SS    | ByProjectID -> DBD   |\n" +
                          "| ByACTIVITY  -> DBA   | ByLEVEL     -> DBL   |\n" +
                          "| MAIN LIST   -> Q     | STOP        -> S     |\n" +
                          "-----------------------------------------------\n";
        System.out.println(getByDev);
        String next = scanner.next();
        if (next.equalsIgnoreCase("byId") | next.equalsIgnoreCase("id")) {
            developerMethods.getById();
        }
        if (next.equalsIgnoreCase("all") | next.equalsIgnoreCase("a")) {
            developerMethods.getAll();
        }
        if (next.equalsIgnoreCase("byProjectId") | next.equalsIgnoreCase("DBD")) {
            return byProjectId();
        }
        if (next.equalsIgnoreCase("byActivity") | next.equalsIgnoreCase("DBA")) {
            return byActivity();
        }
        if (next.equalsIgnoreCase("byLevel") | next.equalsIgnoreCase("DBL")) {
            return byLevel();
        }
        if (next.equalsIgnoreCase("SumSalaries") | next.equalsIgnoreCase("ss")) {
            return getSumSalaries();
        }
        if (next.equalsIgnoreCase("main") | next.equalsIgnoreCase("list") | next.equalsIgnoreCase("q")) {
            start();
            return next;
        }
        if (next.equalsIgnoreCase("stop") | next.equalsIgnoreCase("s") | next.equalsIgnoreCase("sp")) {
            next = "s";
            close();
            return next;
        } else {
            getByDeveloper();
        }
        return next;
    }

    private String byProjectId() {
        special = "byProjectId";
        model = "";
        crud = "";
        return getSpecial();
    }

    private String byActivity() {
        special = "byActivity";
        model = "";
        crud = "";
        return getSpecial();
    }

    private String byLevel() {
        special = "byLevel";
        model = "";
        crud = "";
        return getSpecial();
    }

    private String getSumSalaries() {
        special = "sumSalaries";
        model = "";
        crud = "";
        return getSpecial();
    }

    private void projectsWithDate() {
        special = "withDate";
        model = "";
        crud = "";
        getSpecial();
    }

    @Override
    public void close() {
        System.exit(0);
    }
}