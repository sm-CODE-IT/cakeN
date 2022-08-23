import { useReducer, useEffect, useCallback } from "react";

function reducer(state, action) {
    switch (action.type) {
        case 'LOADING':
            return {
                loading: true,
                data: null,
                error: null
            };
        case 'SUCCESS':
            return {
                loading: false,
                data: action.data,
                error: null
            };
        case 'ERROR':
            return {
                loading: false,
                data: null,
                error: action.error
            };
        default:
            throw new Error("Error !!!");
    }
}


/**
 * Async 방식으로 데이터를 가져오는 함수
 * @param callback
 * @param deps useEffect를 사용할 때 두 번째 파라미터 값을 받아와서 그대로 사용
 */
function useAsync(callback, deps = []) {
    const [state, dispatch] = useReducer(reducer, {
        loading: false,
        data: null,
        error: null
    });
    
    // userCallback은 생략 가능
    const fetchData = useCallback(async () => {
        dispatch( {type: 'LOADING'});
        try {
            const data = await callback();
            dispatch({type: 'SUCCESS', data});
        } catch (e) {
            dispatch({type: 'ERROR', error: e});
        }
    }, [callback]);
    
    useEffect(() => {
        fetchData();
    }, deps);

    return [state, fetchData];
}

export default useAsync;