package org.homework.hibernatehw7.controller;

import org.homework.hibernatehw7.config.ScannerConsole;
import org.homework.hibernatehw7.controller.interfaces.Controller;
import org.homework.hibernatehw7.model.*;

import java.util.Scanner;

public class ControllerConsole implements Controller {

    private final GetDeleteCommands<Developer, Long> developerMethods = new GetDeleteCommands<>(Developer.class);
    private final GetDeleteCommands<Project, Long> projectMethods = new GetDeleteCommands<>(Project.class);
    private final GetDeleteCommands<Company, Long> companyMethods = new GetDeleteCommands<>(Company.class);
    private final GetDeleteCommands<Skill, Long> skillMethods = new GetDeleteCommands<>(Skill.class);
    private final GetDeleteCommands<Customer, Long> customerMethods = new GetDeleteCommands<>(Customer.class);
    private final Scanner scanner = ScannerConsole.getInstance();
    private final String RED = "\u001b[31m";
    private final String YELLOW = "\u001b[33m";
    private final String DARK_BLACK_BACK = "\u001b[40m";
    private String modelName = "";
    private String crudName = "";
    private String specialQuery = "";
    private String result = "";
    private final String startList = YELLOW + DARK_BLACK_BACK +
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
        modelName = "";
        crudName = "";
        specialQuery = "";
        if (read.equalsIgnoreCase("get") | read.equalsIgnoreCase("g")) {
            crudName = "get";
            modelName = getModelName();
            read = crudName + modelName;
            return read;
        }
        if (read.equalsIgnoreCase("create") | read.equalsIgnoreCase("c") | read.equalsIgnoreCase("cre")) {
            crudName = "create";
            modelName = getModelName();
            read = crudName + modelName;
            return read;
        }
        if (read.equalsIgnoreCase("update") | read.equalsIgnoreCase("u") | read.equalsIgnoreCase("up")) {
            crudName = "update";
            modelName = getModelName();
            read = crudName + modelName;
            return read;
        }
        if (read.equalsIgnoreCase("delete") | read.equalsIgnoreCase("d") | read.equalsIgnoreCase("del")) {
            crudName = "delete";
            modelName = getModelName();
            read = crudName + modelName;
            return read;
        }
        if (read.equalsIgnoreCase("developer") | read.equalsIgnoreCase("dev") | read.equalsIgnoreCase("dv")) {
            modelName = "developer";
            crudName = getCrudName();
            read = crudName + modelName;
            return read;
        }
        if (read.equalsIgnoreCase("customer") | read.equalsIgnoreCase("cus") | read.equalsIgnoreCase("cs")) {
            modelName = "customer";
            crudName = getCrudName();
            read = crudName + modelName;
            return read;
        }
        if (read.equalsIgnoreCase("company") | read.equalsIgnoreCase("com") | read.equalsIgnoreCase("cm")) {
            modelName = "company";
            crudName = getCrudName();
            read = crudName + modelName;
            return read;
        }
        if (read.equalsIgnoreCase("skill") | read.equalsIgnoreCase("skl") | read.equalsIgnoreCase("sk")) {
            modelName = "skill";
            crudName = getCrudName();
            read = crudName + modelName;
            return read;
        }
        if (read.equalsIgnoreCase("project") | read.equalsIgnoreCase("pro") | read.equalsIgnoreCase("pr")) {
            modelName = "project";
            crudName = getCrudName();
            read = crudName + modelName;
            return read;
        }
        if (read.equalsIgnoreCase("SPECIAL") | read.equalsIgnoreCase("specR") | read.equalsIgnoreCase("spr")) {
            getSpecialQueries();
            read = specialQuery;
            return read;
        }
        if (read.equalsIgnoreCase("byProjectId") | read.equalsIgnoreCase("DBD")) {
            specialQuery = "byProjectId";
            getSpecialQueries();
            read = specialQuery;
            return read;
        }
        if (read.equalsIgnoreCase("byActivity") | read.equalsIgnoreCase("DBA")) {
            specialQuery = "byActivity";
            getSpecialQueries();
            read = specialQuery;
            return read;
        }
        if (read.equalsIgnoreCase("byLevel") | read.equalsIgnoreCase("DBL")) {
            specialQuery = "byLevel";
            getSpecialQueries();
            read = specialQuery;
            return read;
        }
        if (read.equalsIgnoreCase("withDate") | read.equalsIgnoreCase("PWD")) {
            specialQuery = "withDate";
            getSpecialQueries();
            read = specialQuery;
            return read;
        }
        if (read.equalsIgnoreCase("SumSalaries") | read.equalsIgnoreCase("SS")) {
            specialQuery = "SumSalaries";
            getSpecialQueries();
            read = specialQuery;
            return read;
        }
        if (read.equalsIgnoreCase("list") | read.equalsIgnoreCase("main") | read.equalsIgnoreCase("q")) {
            System.out.println(startList);
            return read;
        }
        if (read.equalsIgnoreCase("all") | read.equalsIgnoreCase("a")) {
            crudName = "all";
            modelName = getModelName();
            read = crudName + modelName;
            return read;
        }
        if (read.equalsIgnoreCase("id") | read.equalsIgnoreCase("byId")) {
            crudName = "id";
            modelName = getModelName();
            read = crudName + modelName;
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

    private String getSpecialQueries() {
        if (modelName.isEmpty() & crudName.isEmpty()) {
            String specials = YELLOW + DARK_BLACK_BACK +
                    "--------------------------------------------------------------------------------------------------\n" +
                    "| PROJECTS   withDATE    -> PWD   | DEVELOPERS ByLEVEL     -> DBL | DEVELOPERS ByACTIVITY -> DBA |\n" +
                    "| DEVELOPERS ByProjectID -> DBD   | DEVELOPERS SumSALARIES -> SS  | MAIN LIST             -> Q   |\n" +
                    "---------------------------------------------------------------------------------------------------\n";
            if (specialQuery.equalsIgnoreCase("withDate") | specialQuery.equalsIgnoreCase("PWD")) {
                specialQuery = "withDate";
                System.out.print("\n  ✅RESULT \uD83D\uDC47");
                projectMethods.getListProjectsWithDate();
                System.out.println(specials);
                return specialQuery;
            }
            if (specialQuery.equalsIgnoreCase("byLevel") | specialQuery.equalsIgnoreCase("DBL")) {
                specialQuery = "byLevel";
                System.out.print("\n  ✅RESULT \uD83D\uDC47\n");
                developerMethods.getByLevel();
                System.out.println(specials);
                return specialQuery;
            }
            if (specialQuery.equalsIgnoreCase("byActivity") | specialQuery.equalsIgnoreCase("DBA")) {
                specialQuery = "byActivity";
                System.out.print("\n  ✅RESULT \uD83D\uDC47\n");
                developerMethods.getByActivity();
                System.out.println(specials);
                return specialQuery;
            }
            if (specialQuery.equalsIgnoreCase("byProjectId") | specialQuery.equalsIgnoreCase("DBD")) {
                specialQuery = "byProjectId";
                System.out.print("\n  ✅RESULT \uD83D\uDC47");
                developerMethods.getByProjectID();
                System.out.println(specials);
                return specialQuery;
            }
            if (specialQuery.equalsIgnoreCase("SumSalaries") | specialQuery.equalsIgnoreCase("SS")) {
                specialQuery = "SumSalaries";
                System.out.print("\n  ✅RESULT \uD83D\uDC47");
                developerMethods.getSumSalaries();
                System.out.println(specials);
                return specialQuery;
            }
            if (specialQuery.equalsIgnoreCase("list") | specialQuery.equalsIgnoreCase("q")) {
                specialQuery = startList;
                start();
                System.out.println(startList);
                return specialQuery;
            }
            if (specialQuery.equalsIgnoreCase("stop") | specialQuery.equalsIgnoreCase("s") | specialQuery.equalsIgnoreCase("sp")) {
                close();
            } else {
                System.out.println(RED + " Choose query \uD83D\uDC47" + specials);
                specialQuery = scanner.next();
                getSpecialQueries();
            }
            return specialQuery;
        }
        return specialQuery;
    }

    private void ifHasModel() {
        if (result.contains("company")) {
            if (result.contains("all")) {
                companyMethods.getAll();
                modelName = "";
                start();
            }
            if (result.contains("id") | result.equalsIgnoreCase("byId")) {
                companyMethods.getById();
                modelName = "";
                start();
            }
            if (result.contains("get")) {
                System.out.println(getBySmall());
                modelName = "";
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
                modelName = "";
                start();
            }
            if (result.contains("all")) {
                customerMethods.getAll();
                modelName = "";
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
                modelName = "";
                start();
            }
            if (result.contains("all")) {
                developerMethods.getAll();
                modelName = "";
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
                modelName = "";
                start();
            }
            if (result.contains("all")) {
                skillMethods.getAll();
                modelName = "";
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
                modelName = "";
                start();
            }
            if (result.contains("all")) {
                projectMethods.getAll();
                modelName = "";
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

    private String getCrudName() {
        if (crudName.isEmpty()) {
            System.out.println(" Choose query ");
            String crudString = YELLOW + DARK_BLACK_BACK +
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
                return getCrudName();
            }
        }
        return crudName;
    }

    private String getModelName() {
        if (modelName.isEmpty()) {
            System.out.println("Выбери модель");
            String getModelList = YELLOW + DARK_BLACK_BACK +
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
                crudName = "";
                return getSpecialQueries();
            }
            if (model.equalsIgnoreCase("main") | model.equalsIgnoreCase("list") | model.equalsIgnoreCase("q")) {
                start();
            }
            if (model.equalsIgnoreCase("stop") | model.equalsIgnoreCase("s") | model.equalsIgnoreCase("sp")) {
                close();
            } else {
                System.out.println(RED + " Please, try again ");
                return getModelName();
            }
        }
        return modelName;
    }

    private String getBySmall() {
        String getBySmall = YELLOW + DARK_BLACK_BACK +
                "_______________________________\n" +
                "| BY ID     -> ID | ALL  -> A |\n" +
                "| MAIN LIST -> Q  | STOP -> S |\n" +
                "-------------------------------";
        System.out.println(getBySmall);
        String next = scanner.next();
        if (next.equalsIgnoreCase("byId") | next.equalsIgnoreCase("id")) {
            if (modelName.equalsIgnoreCase("company")) {
                companyMethods.getById();
                return getBySmall();
            }
            if (modelName.equalsIgnoreCase("customer")) {
                customerMethods.getById();
                return getBySmall();
            }
            if (modelName.equalsIgnoreCase("skill")) {
                skillMethods.getById();
                return getBySmall();
            }

        }
        if (next.equalsIgnoreCase("all") | next.equalsIgnoreCase("a")) {
            if (modelName.equalsIgnoreCase("company")) {
                companyMethods.getAll();
                return getBySmall();
            }
            if (modelName.equalsIgnoreCase("customer")) {
                customerMethods.getAll();
                return getBySmall();
            }
            if (modelName.equalsIgnoreCase("skill")) {
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
        String getBy = YELLOW + DARK_BLACK_BACK +
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
        String getByDev = YELLOW + DARK_BLACK_BACK +
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
        specialQuery = "byProjectId";
        modelName = "";
        crudName = "";
        return getSpecialQueries();
    }

    private String byActivity() {
        specialQuery = "byActivity";
        modelName = "";
        crudName = "";
        return getSpecialQueries();
    }

    private String byLevel() {
        specialQuery = "byLevel";
        modelName = "";
        crudName = "";
        return getSpecialQueries();
    }

    private String getSumSalaries() {
        specialQuery = "sumSalaries";
        modelName = "";
        crudName = "";
        return getSpecialQueries();
    }

    private void projectsWithDate() {
        specialQuery = "withDate";
        modelName = "";
        crudName = "";
        getSpecialQueries();
    }

    @Override
    public void close() {
        System.exit(0);
    }
}