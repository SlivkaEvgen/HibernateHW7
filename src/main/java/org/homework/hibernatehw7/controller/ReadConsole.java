package org.homework.hibernatehw7.controller;

import org.homework.hibernatehw7.config.ScannerConsole;
import org.homework.hibernatehw7.controller.company.CompanyCommandImpl;
import org.homework.hibernatehw7.controller.company.DeleteCompanyCommand;
import org.homework.hibernatehw7.controller.company.GetCompanyCommand;
import org.homework.hibernatehw7.controller.customer.CustomerCommandImpl;
import org.homework.hibernatehw7.controller.customer.GetCustomerCommand;
import org.homework.hibernatehw7.controller.developer.DeveloperCommandImpl;
import org.homework.hibernatehw7.controller.developer.GetDeveloperCommand;
import org.homework.hibernatehw7.controller.interfaces.Controller;
import org.homework.hibernatehw7.controller.project.GetProjectCommand;
import org.homework.hibernatehw7.controller.project.ProjectCommandImpl;
import org.homework.hibernatehw7.controller.skill.GetSkillCommand;
import org.homework.hibernatehw7.controller.skill.SkillCommandImpl;

import java.util.Scanner;

public class ReadConsole implements Controller {

    private final Scanner scanner = ScannerConsole.getInstance();
    private final String RESET = "\u001b[0m";
    private final String RED = "\u001b[31m";
    private final String GREEN = "\u001b[32m";
    private final String YELLOW = "\u001b[33m";
    private final String BLUE = "\u001b[34m";
    private String model = "";
    private String crud = "";
    private String special = "";
    private String result = "";
    private final String startList = YELLOW + "_________________________________________________________________________________________________________________________\n"+
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
            String hasModel = ifHasModel();
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
        if (read.equalsIgnoreCase("project") | read.equalsIgnoreCase("pro")|read.equalsIgnoreCase("pr")) {
            model = "project";
            crud = getCrud();
            read = crud + model;
            return read;
        }
        if (read.equalsIgnoreCase("SPECIAL") | read.equalsIgnoreCase("specr") | read.equalsIgnoreCase("spr")) {
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
        if (read.equalsIgnoreCase("id")|read.equalsIgnoreCase("byid")) {
            crud = "id";
            model = getModel();
            read = crud + model;
            return read;
        }
        if (read.equalsIgnoreCase("stop") | read.equalsIgnoreCase("s") | read.equalsIgnoreCase("sp")) {
            close();
        } else {
            System.out.println(RED+"Введите запрос корректно");
            start();
            return read;
        }
        return getRead();
    }

    private String getSpecial() {
        if (model.isEmpty() & crud.isEmpty()) {
            String specials = BLUE + "\n--------------------------------------------------------------------------------------------------\n" +
                              "| PROJECTS   withDATE    -> PWD   | DEVELOPERS ByLEVEL     -> DBL | DEVELOPERS ByACTIVITY -> DBA |\n" +
                              "| DEVELOPERS ByProjectID -> DBD   | DEVELOPERS SumSALARIES -> SS  | MAIN LIST             -> Q   |\n" +
                              "---------------------------------------------------------------------------------------------------\n";
            if (special.equalsIgnoreCase("withDate") | special.equalsIgnoreCase("PWD")) {
                special = "withDate";
                System.out.print("\n  ✅RESULT \uD83D\uDC47");
                GetProjectCommand.getInstance().getListProjectsWithDate();
                System.out.println(specials);
                return special;
            }
            if (special.equalsIgnoreCase("byLevel") | special.equalsIgnoreCase("DBL")) {
                special = "byLevel";
                System.out.print("\n  ✅RESULT \uD83D\uDC47\n");
                GetDeveloperCommand.getInstance().getByLevel();
                System.out.println(specials);
                return special;
            }
            if (special.equalsIgnoreCase("byActivity") | special.equalsIgnoreCase("DBA")) {
                special = "byActivity";
                System.out.print("\n  ✅RESULT \uD83D\uDC47\n");
                GetDeveloperCommand.getInstance().getByActivity();
                System.out.println(specials);
                return special;
            }
            if (special.equalsIgnoreCase("byProjectId") | special.equalsIgnoreCase("DBPID")) {
                special = "byProjectId";
                System.out.print("\n  ✅RESULT \uD83D\uDC47");
                GetDeveloperCommand.getInstance().getByProjectID();
                System.out.println(specials);
                return special;
            }
            if (special.equalsIgnoreCase("SumSalaries") | special.equalsIgnoreCase("SS")) {
                special = "SumSalaries";
                System.out.print("\n  ✅RESULT \uD83D\uDC47");
                GetDeveloperCommand.getInstance().getSumSalaries();
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
                System.out.println(RED + " Выберите операцию \uD83D\uDC47" + specials);
                special = scanner.next();
                getSpecial();
            }
            return special;
        }
        return special;
    }

    private String ifHasModel() {
        if (result.contains("company")) {
            if (result.contains("all")) {
                GetCompanyCommand.getInstance().getAll();
                model="";
                start();
            }
            if (result.contains("id")|result.equalsIgnoreCase("byid")) {
                GetCompanyCommand.getInstance().getById();
                model="";
                start();
            }
            if (result.contains("get")) {
                System.out.println(getBySmall());
                model="";
                start();
            }
            if (result.contains("update")) {
                CompanyCommandImpl.getInstance().updateCommand();
            }
            if (result.contains("create")) {
                CompanyCommandImpl.getInstance().createCommand();
            }
            if (result.contains("delete")) {
                DeleteCompanyCommand.getInstance().delete();
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
            if (result.contains("id")|result.contains("byid")) {
                GetCustomerCommand.getInstance().getById();
                model="";
                start();
            }
            if (result.contains("all")) {
                GetCustomerCommand.getInstance().getAll();
                model="";
                start();
            }
            if (result.contains("get")) {
                System.out.println(getBySmall());
            }
            if (result.contains("update")) {
                CustomerCommandImpl.getInstance().updateCommand();
            }
            if (result.contains("create")) {
                CustomerCommandImpl.getInstance().createCommand();
            }
            if (result.contains("delete")) {
                CustomerCommandImpl.getInstance().deleteCommand();
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
            if (result.contains("id")|result.contains("byid")) {
                GetDeveloperCommand.getInstance().getById();
                model="";
                start();
            }
            if (result.contains("all")) {
                GetDeveloperCommand.getInstance().getAll();
                model="";
                start();
            }
            if (result.contains("get")) {
                System.out.println(getByDeveloper());
            }
            if (result.contains("update")) {
                DeveloperCommandImpl.getInstance().updateCommand();
            }
            if (result.contains("create")) {
                DeveloperCommandImpl.getInstance().createCommand();
            }
            if (result.contains("delete")) {
                DeveloperCommandImpl.getInstance().deleteCommand();
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
            if (result.contains("id")|result.contains("byid")) {
                GetSkillCommand.getInstance().getById();
                model="";
                start();
            }
            if (result.contains("all")) {
                GetSkillCommand.getInstance().getAll();
                model="";
                start();
            }
            if (result.contains("get")) {
                System.out.println(getBySmall());
            }
            if (result.contains("update")) {
                SkillCommandImpl.getInstance().updateCommand();
            }
            if (result.contains("create")) {
                SkillCommandImpl.getInstance().createCommand();
            }
            if (result.contains("delete")) {
                SkillCommandImpl.getInstance().deleteCommand();
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
            if (result.contains("id")|result.contains("byid")) {
                GetProjectCommand.getInstance().getById();
                model="";
                start();
            }
            if (result.contains("all")) {
                GetProjectCommand.getInstance().getAll();
                model="";
                start();
            }
            if (result.contains("get")) {
                getByProject();
            }
            if (result.contains("update")) {
                ProjectCommandImpl.getInstance().updateCommand();
            }
            if (result.contains("create")) {
                ProjectCommandImpl.getInstance().createCommand();
            }
            if (result.contains("delete")) {
                ProjectCommandImpl.getInstance().deleteCommand();
            }
            if (result.equalsIgnoreCase("main") | result.equalsIgnoreCase("list") | result.equalsIgnoreCase("q")) {
                start();
            }
            if (result.equalsIgnoreCase("stop") | result.equalsIgnoreCase("s") | result.equalsIgnoreCase("sp")) {
                close();
            }
        }
        return result;
    }

    private String getCrud() {
        if (crud.isEmpty()) {
            System.out.println("Выбери операцию");
            String crudString = BLUE + "______________________________________________\n" +
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
                System.out.println(RED+"введите правильно операцию");
                return getCrud();
            }
        }
        return crud;
    }

    private String getModel() {
        if (model.isEmpty()) {
            System.out.println("Выбери модель");
            String getModelList = BLUE + "____________________________________________________\n" +
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
                System.out.println(RED+"введите правильно название модели");
                return getModel();
            }
        }
        return model;
    }

    @Override
    public void close() {
        System.exit(0);
    }

    private String getBySmall() {
        String getBySmall = BLUE + "_______________________________\n" +
                            "| BY ID     -> ID | ALL  -> A |\n" +
                            "| MAIN LIST -> Q  | STOP -> S |\n" +
                            "-------------------------------";
        System.out.println(getBySmall);
        String next = scanner.next();
        if (next.equalsIgnoreCase("byid") | next.equalsIgnoreCase("id")) {
            if (model.equalsIgnoreCase("company")) {
                GetCompanyCommand.getInstance().getById();
                return getBySmall();
            }
            if (model.equalsIgnoreCase("customer")) {
                GetCustomerCommand.getInstance().getById();
                return getBySmall();
            }
            if (model.equalsIgnoreCase("skill")) {
                GetSkillCommand.getInstance().getById();
                return getBySmall();
            }

        }
        if (next.equalsIgnoreCase("all") | next.equalsIgnoreCase("a")) {
            if (model.equalsIgnoreCase("company")) {
                GetCompanyCommand.getInstance().getAll();
                return getBySmall();
            }
            if (model.equalsIgnoreCase("customer")) {
                GetCustomerCommand.getInstance().getAll();
                return getBySmall();
            }
            if (model.equalsIgnoreCase("skill")) {
                GetSkillCommand.getInstance().getAll();
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

    private String getByProject() {
        String getBy = BLUE + "_______________________________________________\n" +
                       "| BY ID       -> ID    | All         -> A     |\n" +
                       "| WithDATE    -> PWD   | MAIN LIST   -> Q     |\n" +
                       "|                      | STOP        -> S     |\n" +
                       "-----------------------------------------------\n";
        System.out.println(getBy);
        String next = scanner.next();
        if (next.equalsIgnoreCase("byid") | next.equalsIgnoreCase("id")) {
            GetProjectCommand.getInstance().getById();
        }
        if (next.equalsIgnoreCase("all") | next.equalsIgnoreCase("a")) {
            GetProjectCommand.getInstance().getAll();
        }
        if (next.equalsIgnoreCase("withDate") | next.equalsIgnoreCase("PWD")) {
            return projectsWithDate();
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
             getByProject();
        }
        return next;
    }

    private String getByDeveloper() {
        String getByDev = YELLOW + "_______________________________________________\n" +
                          "| BY ID       -> ID    | All         -> A     |\n" +
                          "| SumSALARIES -> SS    | ByProjectID -> DBD   |\n" +
                          "| ByACTIVITY  -> DBA   | ByLEVEL     -> DBL   |\n" +
                          "| MAIN LIST   -> Q     | STOP        -> S     |\n" +
                          "-----------------------------------------------\n";
        System.out.println(getByDev);
        String next = scanner.next();
        if (next.equalsIgnoreCase("byid") | next.equalsIgnoreCase("id")) {
            GetDeveloperCommand.getInstance().getById();
        }
        if (next.equalsIgnoreCase("all") | next.equalsIgnoreCase("a")) {
            GetDeveloperCommand.getInstance().getAll();
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

    private String projectsWithDate() {
        special = "withDate";
        model = "";
        crud = "";
        getSpecial();
        return getByProject();
    }
}
