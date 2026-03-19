package com.example.demo;

import com.example.demo.Models.*;
import com.example.demo.Repos.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class DatabaseInit implements CommandLineRunner {

	private static final String DATE_FEB_20 = "2026-02-20";
	private static final String DATE_FEB_25 = "2026-02-25";
	private static final String DATE_MAR_01 = "2026-03-01";
	private static final String FILE_FORMAT_CHECK = "FILE_FORMAT_CHECK";

	private final UserRepository userRepository;
	private final ContractorRepository contractorRepository;
	private final RegionRepository regionRepository;
	private final ReportRepository reportRepository;
	private final ReportFileRepository reportFileRepository;
	private final ReportValidationRepository reportValidationRepository;

	public DatabaseInit(UserRepository userRepository,
			ContractorRepository contractorRepository,
			RegionRepository regionRepository,
			ReportRepository reportRepository,
			ReportFileRepository reportFileRepository,
			ReportValidationRepository reportValidationRepository) {
		this.userRepository = userRepository;
		this.contractorRepository = contractorRepository;
		this.regionRepository = regionRepository;
		this.reportRepository = reportRepository;
		this.reportFileRepository = reportFileRepository;
		this.reportValidationRepository = reportValidationRepository;
	}

	@Override
	public void run(String... args) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		Date dateFeb20 = sdf.parse(DATE_FEB_20);
		Date dateFeb25 = sdf.parse(DATE_FEB_25);
		Date dateMar01 = sdf.parse(DATE_MAR_01);

		// Users
		userRepository.deleteAll();
		User user1 = new User("scientist@oceaniq.org", "sci_secure_pass", true, true);
		User user2 = new User("admin@oceaniq.org", "adm_secure_pass", true, true);
		User user3 = new User("researcher@marinehub.org", "res_secure_pass", true, true);
		userRepository.save(user1);
		userRepository.save(user2);
		userRepository.save(user3);

		// Contractors
		contractorRepository.deleteAll();
		Contractor contractor1 = new Contractor("DeepSea Minerals Ltd", "DSM-2034", "NO");
		Contractor contractor2 = new Contractor("Ocean Extract Corp", "OEC-9981", "JP");
		Contractor contractor3 = new Contractor("Pacific Ridge Mining", "PRM-5512", "US");
		contractorRepository.save(contractor1);
		contractorRepository.save(contractor2);
		contractorRepository.save(contractor3);

		// Regions
		regionRepository.deleteAll();
		Region region1 = new Region("Clarion-Clipperton Zone", "Large deep-sea mining exploration zone in Pacific");
		Region region2 = new Region("Mid-Atlantic Ridge", "Hydrothermal vent ecosystem");
		Region region3 = new Region("Indian Ocean Basin", "Region with polymetallic nodules");
		regionRepository.save(region1);
		regionRepository.save(region2);
		regionRepository.save(region3);

		// Reports
		reportRepository.deleteAll();
		Report report1 = new Report(1, 1, 1, ReportType.COMPLIANCE,
				"Pacific Nodule Mining Impact Study", "EIA study for Pacific region",
				ReportStatus.SUBMITTED, dateFeb20);
		Report report2 = new Report(3, 2, 2, ReportType.TECHNICAL,
				"Atlantic Ridge Ecosystem Assessment", "REMP assessment for Atlantic ridge",
				ReportStatus.APPROVED, dateFeb25);
		Report report3 = new Report(1, 3, 3, ReportType.AUDIT,
				"Indian Basin Conservation Plan", "Conservation plan for Indian Ocean Basin",
				ReportStatus.SUBMITTED, dateMar01);
		reportRepository.save(report1);
		reportRepository.save(report2);
		reportRepository.save(report3);

		// Report Files
		reportFileRepository.deleteAll();
		ReportFile file1 = new ReportFile(1, "pacific_eia_summary.pdf",
				"/reports/eia_pacific.pdf", "pdf", 204800, null, dateFeb20);
		ReportFile file2 = new ReportFile(2, "atlantic_remp_report.pdf",
				"/reports/remp_atlantic.pdf", "pdf", 350000, null, dateFeb25);
		ReportFile file3 = new ReportFile(3, "apei_conservation_plan.pdf",
				"/reports/apei_indian.pdf", "pdf", 180000, null, dateMar01);
		reportFileRepository.save(file1);
		reportFileRepository.save(file2);
		reportFileRepository.save(file3);

		// Report Validations
		reportValidationRepository.deleteAll();
		Reportvalidation val1 = new Reportvalidation(1, FILE_FORMAT_CHECK,
				ValidationResult.PASSED, "Report uploaded in valid PDF format", dateFeb20);
		Reportvalidation val2 = new Reportvalidation(1, "REQUIRED_FIELDS_CHECK",
				ValidationResult.PASSED, "All mandatory report fields are filled", dateFeb20);
		Reportvalidation val3 = new Reportvalidation(2, FILE_FORMAT_CHECK,
				ValidationResult.PASSED, "Valid document format", dateFeb25);
		Reportvalidation val4 = new Reportvalidation(2, "DATA_COMPLETENESS",
				ValidationResult.PASSED, "Environmental metrics included", dateFeb25);
		Reportvalidation val5 = new Reportvalidation(3, FILE_FORMAT_CHECK,
				ValidationResult.PASSED, "Valid PDF document", dateMar01);
		reportValidationRepository.save(val1);
		reportValidationRepository.save(val2);
		reportValidationRepository.save(val3);
		reportValidationRepository.save(val4);
		reportValidationRepository.save(val5);
	}
}