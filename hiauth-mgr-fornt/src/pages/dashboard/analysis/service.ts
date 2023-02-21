import {request} from 'umi';
import type {AnalysisData} from './data';

// export async function fakeChartData(): Promise<{ data: AnalysisData }> {
//   return request('/api/fake_analysis_chart_data');
// }

export async function fakeChartData(
  options?: Record<string, any>
) {
  return request<API.BasicApiResponse<AnalysisData>>('/api/analysis/dashboard', {
    method: 'POST',
    ...(options || {}),
  });
}
