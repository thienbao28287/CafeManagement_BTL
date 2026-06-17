package service;

import model.ThongKeDashboard;
import repository.IThongKeRepository;
import repository.ThongKeRepositoryImpl;

public class ThongKeServiceImpl implements IThongKeService {
    private final IThongKeRepository repo = new ThongKeRepositoryImpl();

    @Override
    public ThongKeDashboard getDashboardStats() {
        return repo.getDashboardStats();
    }
}
