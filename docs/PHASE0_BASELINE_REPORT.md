# Phase 0: Legacy Baseline Report — T2Ti ERP (JavaERP_Repo)

**Date:** 2026-04-01  
**JDK Version:** OpenJDK 1.8.0_482  
**Build Tool:** Apache Ant 1.10.12  
**Target Modernization:** Spring Boot 3.x + JDK 21 + Gradle

---

## 1. Repository Overview

| Metric | Value |
|---|---|
| Total Modules | 36 (NetBeans/Ant WAR projects) |
| Total Java Source Files | 6,161 |
| Total Lines of Code | 1,228,175 |
| Existing Tests | **0** (zero test files across all modules) |
| Build System | Apache Ant (NetBeans-generated `build-impl.xml`) |
| Packaging | WAR (Java Web Application for Tomcat) |
| Java Source Level | 1.7 (Cadastros, Sintegra, SpedFiscal, SpedContribuicoes, NFe) / 1.8 (all others) |
| Client Architecture | OpenSwing rich desktop client via JNLP/Java Web Start |
| Server Communication | Hessian binary RPC protocol |
| Persistence | JPA 2.0 + Hibernate 3.6.10.Final + PostgreSQL 9.3 |
| Deployment | Tomcat (WAR files deployed via Tomcat Manager) |

---

## 2. Directory Structure

```
JavaERP_Repo/
├── libs/                          # Shared dependency JARs
│   ├── apache/                    # commons-io-2.0, commons-lang-2.4
│   ├── liquidnf/                  # liquidlnf.jar (Liquid Look&Feel)
│   ├── openswing/                 # OpenSwing framework (18 JARs)
│   ├── postgresql/                # postgresql-9.3-1102.jdbc41.jar
│   └── texgit/                    # JRimum-Texgit (Brazilian banking)
├── AgendaCorporativa/             # Module (101 files, 16,410 LOC)
├── Cadastros/                     # Module (343 files, 55,740 LOC)
├── ... (34 more modules)
├── T2Ti_ERP_URS_and_TestCases.html  # URS/Test Case documentation
├── LICENSE
└── README.md
```

Each module follows the standard NetBeans Web Application layout:
```
ModuleName/
├── build.xml                      # Custom Ant targets (signlib, t2tierp)
├── manifest.txt                   # JNLP security permissions
├── nbproject/
│   ├── build-impl.xml             # NetBeans-generated build logic (~1,500 lines)
│   ├── project.properties         # Dependencies, source/target levels
│   ├── project.xml                # Project metadata, library references
│   └── ant-deploy.xml             # Tomcat deployment configuration
├── src/
│   ├── java/                      # Java source files
│   │   └── com/t2tierp/
│   │       ├── <module>/cliente/  # Client-side (Swing UI) classes
│   │       ├── <module>/java/     # Shared VO/DTO classes (JPA entities)
│   │       ├── <module>/servidor/ # Server-side action handlers
│   │       └── padrao/            # Shared base classes (duplicated per module)
│   └── conf/
│       └── MANIFEST.MF
└── web/
    └── WEB-INF/
        └── web.xml
```

---

## 3. `libs/` Directory Analysis

### 3.1 JARs Present in Repository (23 JARs)

| Directory | JARs | Description |
|---|---|---|
| `libs/openswing/` | 18 JARs | OpenSwing framework: clientos, commonos, serveros, hessian-3.1.6, iText, jcalendar, hsqldb, log4j, etc. |
| `libs/apache/` | 2 JARs | commons-io-2.0, commons-lang-2.4 |
| `libs/postgresql/` | 1 JAR | postgresql-9.3-1102.jdbc41.jar |
| `libs/liquidnf/` | 1 JAR | liquidlnf.jar (Liquid Look & Feel) |
| `libs/texgit/` | 1 JAR | JRimum-Texgit-Utilix-0.2.0-SNAPSHOT-20101207.jar |

### 3.2 Missing JARs (93 JARs across 8 library groups)

| Missing Library Group | # JARs | Modules Affected | Available on Maven Central? |
|---|---|---|---|
| `jpa_hibernate/` | 15 | **ALL 36 modules** (via AgendaCorporativa pattern or Cadastros `libs.hibernate-persistence` pattern) | **Yes** — All downloaded and verified |
| `apache/axis2/` | 67 | CTe, NFSe, NFe, WMS, eSocial | Yes |
| `jasper/` | 4 | Contabilidade, Folha, GeradorEtiqueta, OrdemServico, Ponto + 31 others (in project.properties but not all import) | Yes |
| `bopepo/` | 1 | FinanceiroCaixaBanco, FinanceiroFluxoCaixa, FinanceiroTesouraria (signlib phase) | Yes (JRimum/Bopepo) |
| `openoffice/` | 5 | Comissoes, Contratos, ECD, Inventario, PCP, Vendas | Yes (LibreOffice UNO) |
| `mmscomputing/` | 1 | AgendaCorporativa, CTe, Ged, GeradorEtiqueta, NFSe, OrdemServico, WMS, eSocial | Difficult (proprietary scanner lib) |
| `zxing/` | 2 | CTe, GeradorEtiqueta, NFSe, OrdemServico, WMS, eSocial | Yes |
| `texgit/` (second JAR) | 1 | Comissoes, ConciliacaoContabil + 14 others | Yes (JRimum-Texgit) |

### 3.3 Other Missing Build Artifacts

| Missing Item | Impact |
|---|---|
| `../chaves/chaves` (keystore) | JAR signing fails for ALL modules after successful compilation |
| NetBeans CopyLibs task JAR | Required for Ant build initialization (created stub for this analysis) |
| Tomcat installation (`j2ee.server.home`) | Servlet API not on classpath; J2EE platform classpath unresolved |
| NetBeans library definitions (`libs.hibernate-persistence`, `libs.javaee-endorsed-api-6.0`) | Must be provided as Ant properties |

---

## 4. Build Results — All 36 Modules

### 4.1 Build Methodology

```bash
export JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64
ant compile \
  -Dlibs.CopyLibs.classpath=/path/to/copylibstask.jar \
  -Dlibs.hibernate-persistence.classpath=<all 15 Hibernate JARs> \
  -Dj2ee.platform.classpath=<servlet-api + jsp-api> \
  -Dlibs.javaee-endorsed-api-6.0.classpath=<servlet-api + jsp-api>
```

### 4.2 Summary

| Build Outcome | Count | Modules |
|---|---|---|
| **Compilation SUCCESS** (fails only at post-compile JAR signing) | **19** | AgendaCorporativa, Cadastros, Comissoes, Compras, ConciliacaoContabil, ECD, FinanceiroCaixaBanco, FinanceiroContasPagar, FinanceiroFluxoCaixa, FinanceiroGeral, FinanceiroTesouraria, Inventario, Orcamentos, PCP, Patrimonio, Sintegra, SpedContribuicoes, SpedFiscal, Tributacao, Vendas, WMS, eSocial |
| **Compilation FAILURE** (missing libraries) | **14** | CTe, Contabilidade, Contratos, ControleEstoque, EscritaFiscal, FinanceiroConciliacaoBancaria, FinanceiroContasReceber, Folha, Ged, GeradorEtiqueta, NFSe, NFe, OrdemServico, Ponto |
| **Encoding errors** (source files not UTF-8) | **2** | ControleEstoque (100 errors), EscritaFiscal (100 errors) |

### 4.3 Modules That Compile Successfully (19 modules)

These modules compile with zero errors. They fail only in the `-post-compile` phase at `signlib` (JAR signing) due to missing keystore file `../chaves/chaves`:

| Module | Files | LOC | Post-compile Failure |
|---|---|---|---|
| AgendaCorporativa | 101 | 16,410 | Missing keystore |
| Cadastros | 343 | 55,740 | Missing keystore |
| Comissoes | 96 | 15,135 | Missing keystore + missing `libs/bopepo` |
| Compras | 135 | 22,807 | Missing keystore |
| ConciliacaoContabil | 174 | 26,599 | Missing keystore + missing `libs/bopepo` |
| ECD | 176 | 25,319 | Missing keystore |
| FinanceiroCaixaBanco | 153 | 25,076 | Missing `libs/bopepo` dir |
| FinanceiroContasPagar | 153 | 26,362 | Missing keystore |
| FinanceiroFluxoCaixa | 147 | 24,075 | Missing `libs/bopepo` dir |
| FinanceiroGeral | 178 | 28,323 | Missing keystore |
| FinanceiroTesouraria | 168 | 27,980 | Missing `libs/bopepo` dir |
| Inventario | 113 | 17,529 | Missing keystore |
| Orcamentos | 126 | 21,286 | Missing keystore |
| PCP | 142 | 23,372 | Missing keystore |
| Patrimonio | 124 | 20,807 | Missing keystore |
| Sintegra | 182 | 30,803 | Missing keystore |
| SpedContribuicoes | 249 | 39,868 | Missing keystore |
| SpedFiscal | 261 | 43,122 | Missing keystore |
| Tributacao | 110 | 18,196 | Missing keystore |
| Vendas | 138 | 24,315 | Missing keystore |
| WMS | 134 | 21,600 | Missing keystore |
| eSocial | 105 | 21,268 | Missing keystore |

### 4.4 Modules That Fail Compilation (14 modules)

| Module | Files | LOC | Errors | Root Cause |
|---|---|---|---|---|
| **CTe** | 217 | 76,901 | 100 | Missing `apache/axis2` (32 errors), `org.apache.axis2.databinding` (26), `org.apache.axiom.om` (22) |
| **NFe** | 347 | 141,228 | 100 | Missing `apache/axis2` (32), `axis2.databinding` (31), `axiom.om` (20) |
| **NFSe** | 183 | 38,836 | 100 | Missing `apache/axis2` (31), `axis2.databinding` (24), `axiom.om` (21) |
| **ControleEstoque** | 268 | 82,461 | 100 | **Encoding:** `unmappable character for encoding UTF-8` (files are Latin-1/ISO-8859-1) |
| **EscritaFiscal** | 303 | 88,516 | 100 | **Encoding:** `unmappable character for encoding UTF-8` (same as ControleEstoque) |
| **Contabilidade** | 233 | 35,732 | 27 | Missing `jasper/` JARs: `net.sf.jasperreports.*` (9 errors) |
| **Contratos** | 137 | 23,050 | 63 | Missing `openoffice/` JARs: `com.sun.star.*` (40+ errors) |
| **FinanceiroConciliacaoBancaria** | 154 | 25,018 | 20 | Missing `net.sf.ofx4j.*` OFX parsing library (5 errors) |
| **FinanceiroContasReceber** | 173 | 30,559 | 42 | Missing `org.jrimum.*` Bopepo/JRimum banking library (11 errors) |
| **Folha** | 211 | 37,443 | 14 | Missing `jasper/` JARs (3 errors) |
| **Ged** | 87 | 14,417 | 12 | Missing `mmscomputing/` scanner library (4 errors) |
| **GeradorEtiqueta** | 90 | 14,920 | 61 | Missing `jasper/` (14 errors) + `zxing/` (7 errors) |
| **OrdemServico** | 104 | 17,386 | 9 | Missing `jasper/` JARs (3 errors) |
| **Ponto** | 146 | 25,716 | 9 | Missing `jasper/` JARs (3 errors) |

---

## 5. Cross-Module Package Duplication

A key architectural concern: **shared base packages are duplicated across every module** rather than being extracted into a shared library. The `padrao` (standard/base) and `cadastros` packages appear in all 36 modules:

| Package | Appears In |
|---|---|
| `com.t2tierp.padrao` (cliente/java/servidor) | **All 36 modules** |
| `com.t2tierp.cadastros` (java) | **All 36 modules** |
| `com.t2tierp.tributacao` (java) | 32 modules |
| `com.t2tierp.contabilidade` (java) | 19 modules |
| `com.t2tierp.comissao` (java) | 16 modules |
| `com.t2tierp.financeiro` (java) | 12 modules |
| `com.t2tierp.vendas` (java) | 8 modules |
| `com.t2tierp.ged` (java) | 10 modules |
| `com.t2tierp.nfe` (java) | 9 modules |
| `com.t2tierp.escritafiscal` (java) | 5 modules |
| `com.t2tierp.controleestoque` | 4 modules |

**Impact:** Each module ships its own copy of the "padrao" server framework, entity VOs, and Hibernate utilities. Changes to shared entities must be synchronized across all 36 modules manually.

---

## 6. Technology Stack Inventory

### 6.1 Runtime Dependencies

| Technology | Version | Purpose |
|---|---|---|
| JDK | 1.7 / 1.8 | Compilation target |
| Apache Tomcat | 7.x / 8.x | Servlet container |
| Hibernate Core | 3.6.10.Final | ORM / JPA provider |
| Hibernate JPA API | 2.0 (1.0.1.Final) | JPA annotations |
| PostgreSQL JDBC | 9.3-1102 (JDBC 4.1) | Database driver |
| OpenSwing | Custom build | Client/Server UI framework |
| Hessian | 3.1.6 | Binary RPC protocol |
| C3P0 | 0.9.1 | Connection pooling |
| Log4j | 1.2.7 | Logging |
| SLF4J | 1.6.1 | Logging facade |
| iText | 1.4.8 / 2.1.7 | PDF generation |
| Apache POI | 2.0-RC2 | Excel export |
| JCalendar | - | Date picker UI |
| Liquid Look & Feel | - | Swing UI theme |

### 6.2 Additional Dependencies (per module group)

| Technology | Version | Used By |
|---|---|---|
| Apache Axis2 | 1.6.2 (67 JARs) | CTe, NFe, NFSe, WMS, eSocial (SOAP web services for Brazilian fiscal docs) |
| JasperReports | 5.6.0 | ~6 modules (report generation) |
| JRimum/Bopepo | - | Financial modules (Brazilian bank boleto/slip generation) |
| JRimum Texgit | 0.2.0-SNAPSHOT | Financial modules (Brazilian banking text file parsing) |
| LibreOffice UNO API | - | 6 modules (document generation) |
| MmsComputing | - | Ged, GeradorEtiqueta (scanner hardware integration) |
| ZXing | - | 6 modules (barcode/QR code generation) |
| OFX4J | - | FinanceiroConciliacaoBancaria (OFX bank data import) |

---

## 7. Architecture Patterns

### 7.1 Three-Tier Architecture (per module)

```
┌─────────────────────────────────────────┐
│           JNLP Client (Swing)           │
│  com.t2tierp.<module>.cliente.*         │
│  ├── Grid controllers                   │
│  ├── Detail form controllers            │
│  └── OpenSwing UI bindings              │
├─────────────────────────────────────────┤
│         Hessian RPC (HTTP)              │
├─────────────────────────────────────────┤
│           Servlet Server                │
│  com.t2tierp.<module>.servidor.*        │
│  ├── *GridAction.java (list/query)      │
│  ├── *DetalheAction.java (CRUD detail)  │
│  └── HibernateUtil.java (session mgmt) │
├─────────────────────────────────────────┤
│         JPA / Hibernate 3.6             │
│  com.t2tierp.<module>.java.*            │
│  ├── *VO.java (Value Objects / Entities)│
│  └── @Entity, @Table, @Column, etc.    │
├─────────────────────────────────────────┤
│           PostgreSQL Database           │
└─────────────────────────────────────────┘
```

### 7.2 Common Code Patterns

- **Entity naming:** `*VO.java` (Value Object pattern, but actually JPA entities)
- **Server actions:** `*GridAction.java` (list/query), `*DetalheAction.java` (CRUD operations)
- **Client controllers:** `*Grid.java`, `*Detalhe.java` (OpenSwing grid/form controllers)
- **Session management:** `HibernateUtil.java` using `EntityManagerFactory` (duplicated in every module)
- **Servlet mapping:** All requests via Hessian servlet dispatcher

---

## 8. Modernization Risk Assessment

### 8.1 High-Impact Concerns

| Risk | Severity | Details |
|---|---|---|
| Code duplication | **Critical** | `padrao/` and `cadastros/` packages duplicated across all 36 modules (~36x copies of HibernateUtil, base VOs) |
| No test coverage | **Critical** | Zero tests across 1.2M+ LOC. Any refactoring is blind. |
| Hessian RPC coupling | **High** | Client-server communication tightly coupled to Hessian binary protocol; OpenSwing framework enforces this |
| OpenSwing dependency | **High** | The entire server-side action pattern (`GridAction`, `DetalheAction`) is dictated by OpenSwing's server-side controller interfaces |
| Encoding inconsistency | **Medium** | At least 2 modules (ControleEstoque, EscritaFiscal) have Latin-1 encoded source files despite `source.encoding=UTF-8` |
| Missing keystore | **Medium** | All JNLP client packaging is blocked without the original code signing certificate |
| Brazil-specific libs | **Medium** | Heavy reliance on JRimum/Bopepo (banking), Axis2 SOAP stubs for NF-e/CT-e/NFS-e (Brazilian tax docs) |

### 8.2 Modernization Path Recommendations

1. **Phase 1 — Shared Library Extraction:** Extract `padrao`, `cadastros`, `tributacao`, `contabilidade`, `comissao` into a single shared module to eliminate 36x duplication.

2. **Phase 2 — Gradle Multi-Project Build:** Convert from 36 independent Ant WAR projects to a single Gradle multi-module project with a shared `core` module.

3. **Phase 3 — Spring Boot Migration:** Replace OpenSwing server actions with Spring MVC REST controllers. The entity VOs can largely remain as-is (rename from JPA 2.0 `javax.persistence` to `jakarta.persistence`).

4. **Phase 4 — Hibernate Upgrade:** Migrate from Hibernate 3.6 → 6.x (JPA 3.1), update PostgreSQL driver, replace C3P0 with HikariCP.

5. **Phase 5 — Test Coverage:** Write integration tests for the server-side action handlers first (they contain all business logic), then unit tests for entity validation.

---

## 9. Appendix: Module Inventory

| # | Module | Files | LOC | Compiles? | Domain |
|---|---|---|---|---|---|
| 1 | AgendaCorporativa | 101 | 16,410 | Yes | Corporate Agenda/Scheduling |
| 2 | CTe | 217 | 76,901 | No (Axis2) | CT-e Electronic Transport Document |
| 3 | Cadastros | 343 | 55,740 | Yes | Base Registrations (People, Companies, etc.) |
| 4 | Comissoes | 96 | 15,135 | Yes | Sales Commissions |
| 5 | Compras | 135 | 22,807 | Yes | Purchasing |
| 6 | ConciliacaoContabil | 174 | 26,599 | Yes | Accounting Reconciliation |
| 7 | Contabilidade | 233 | 35,732 | No (Jasper) | Accounting/General Ledger |
| 8 | Contratos | 137 | 23,050 | No (OpenOffice) | Contracts |
| 9 | ControleEstoque | 268 | 82,461 | No (Encoding) | Inventory Control |
| 10 | ECD | 176 | 25,319 | Yes | Digital Accounting Bookkeeping |
| 11 | EscritaFiscal | 303 | 88,516 | No (Encoding) | Tax Bookkeeping |
| 12 | FinanceiroCaixaBanco | 153 | 25,076 | Yes | Finance: Cash & Bank |
| 13 | FinanceiroConciliacaoBancaria | 154 | 25,018 | No (OFX4J) | Finance: Bank Reconciliation |
| 14 | FinanceiroContasPagar | 153 | 26,362 | Yes | Finance: Accounts Payable |
| 15 | FinanceiroContasReceber | 173 | 30,559 | No (JRimum) | Finance: Accounts Receivable |
| 16 | FinanceiroFluxoCaixa | 147 | 24,075 | Yes | Finance: Cash Flow |
| 17 | FinanceiroGeral | 178 | 28,323 | Yes | Finance: General |
| 18 | FinanceiroTesouraria | 168 | 27,980 | Yes | Finance: Treasury |
| 19 | Folha | 211 | 37,443 | No (Jasper) | Payroll |
| 20 | Ged | 87 | 14,417 | No (MmsComputing) | Document Management |
| 21 | GeradorEtiqueta | 90 | 14,920 | No (Jasper+ZXing) | Label Generator |
| 22 | Inventario | 113 | 17,529 | Yes | Physical Inventory |
| 23 | NFSe | 183 | 38,836 | No (Axis2) | NFS-e Electronic Service Invoice |
| 24 | NFe | 347 | 141,228 | No (Axis2) | NF-e Electronic Invoice |
| 25 | Orcamentos | 126 | 21,286 | Yes | Quotations/Budgets |
| 26 | OrdemServico | 104 | 17,386 | No (Jasper) | Service Orders |
| 27 | PCP | 142 | 23,372 | Yes | Production Planning & Control |
| 28 | Patrimonio | 124 | 20,807 | Yes | Asset Management |
| 29 | Ponto | 146 | 25,716 | No (Jasper) | Time & Attendance |
| 30 | Sintegra | 182 | 30,803 | Yes | SINTEGRA Tax Reporting |
| 31 | SpedContribuicoes | 249 | 39,868 | Yes | SPED Contributions |
| 32 | SpedFiscal | 261 | 43,122 | Yes | SPED Fiscal |
| 33 | Tributacao | 110 | 18,196 | Yes | Taxation |
| 34 | Vendas | 138 | 24,315 | Yes | Sales |
| 35 | WMS | 134 | 21,600 | Yes | Warehouse Management |
| 36 | eSocial | 105 | 21,268 | Yes | eSocial (Brazilian labor reporting) |

---

## 10. NetBeans Library Definitions Required

To build outside NetBeans IDE, these properties must be supplied via `-D` flags:

| Property | Value |
|---|---|
| `libs.CopyLibs.classpath` | Path to `org-netbeans-modules-java-j2seproject-copylibstask.jar` |
| `libs.hibernate-persistence.classpath` | All 15 Hibernate/JPA JARs (colon-separated) |
| `libs.javaee-endorsed-api-6.0.classpath` | `javax.servlet-api-3.0.1.jar:javax.servlet.jsp-api-2.2.1.jar` |
| `j2ee.platform.classpath` | Tomcat lib JARs (servlet-api, jsp-api, etc.) |
| `j2ee.server.home` | Tomcat installation directory |

---

*This report was generated as part of Phase 0: Legacy Baseline analysis for the T2Ti ERP modernization project.*
