package hr.agency04.bagy94.common_java;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import static androidx.navigation.Navigation.findNavController;

public abstract class BaseFragment<VIEWMODEL extends BaseViewModel, BINDING extends ViewDataBinding> extends Fragment {

    abstract protected int getViewId();

    abstract protected Class<VIEWMODEL> getViewModelClass();

    protected VIEWMODEL mViewModel;
    protected BINDING mViewBinding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = provideViewModel();
        mViewModel.getRouter().observe(this);
        if (getArguments() != null) {
            onParseArguments(getArguments());
        }
    }

    protected VIEWMODEL provideViewModel() {
        return ViewModelProviders.of(this).get(getViewModelClass());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mViewBinding = createDataBinding(inflater, container);
        mViewBinding.setLifecycleOwner(this);
        return mViewBinding.getRoot();
    }

    protected BINDING createDataBinding(LayoutInflater inflater, ViewGroup container) {
        return DataBindingUtil.inflate(inflater, getViewId(), container, false);
    }


    public void back() {
        View view = getView();
        if (view != null) {
            findNavController(view).navigateUp();
        }
    }

    public void onParseArguments(Bundle arguments) {

    }

    protected void navigate(int action, Bundle args) {
        View v = getView();
        if (v != null) {
            findNavController(v).navigate(action, args);
        }
    }
}
